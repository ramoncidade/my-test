package br.com.cidade.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cidade.bean.utils.GenericResponse;
import br.com.cidade.business.AddressBusiness;
import br.com.cidade.entity.AddressEntity;

@RestController
@RequestMapping(path = AddressController.BASE_PATH, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AddressController {

	static final String BASE_PATH = "/rest/address";

	private AddressBusiness business;

	public AddressController(AddressBusiness business) {
		this.business = business;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<GenericResponse<AddressEntity>> get(@PathVariable Long id) {
		Optional<AddressEntity> result = business.findOne(new AddressEntity(id));
		if (result.isPresent()) {
			return new ResponseEntity<GenericResponse<AddressEntity>>(new GenericResponse<AddressEntity>(result.get(), "Endereco encontrado"), HttpStatus.OK);
		} else {
			return new ResponseEntity<GenericResponse<AddressEntity>>(new GenericResponse<AddressEntity>(null,"Endereco nao encontrado"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<GenericResponse<Page<AddressEntity>>> get(Pageable page) {
		Page<AddressEntity> result = business.findAll(page);
		GenericResponse<Page<AddressEntity>> response=  new GenericResponse<Page<AddressEntity>>(result);
		HttpStatus status;
		if(result != null && result.hasContent()){
			status = HttpStatus.OK;
		}else{
			status = HttpStatus.NOT_FOUND;
			response.setMessage("Endereco nao encontrado");
		}
		return new ResponseEntity<GenericResponse<Page<AddressEntity>>>(response,status);
	}

	@PostMapping
	public ResponseEntity<GenericResponse<AddressEntity>> create(@Valid @RequestBody AddressEntity address, BindingResult bindingResults) {
		GenericResponse<AddressEntity> response;
		HttpStatus status;
		if (!bindingResults.hasErrors() && business.isValidZipcode(address)) {
			response = new GenericResponse<AddressEntity>(this.business.save(address));
			status = HttpStatus.OK;
		} else {
			response = new GenericResponse<AddressEntity>(address, "Endereco no formato incorreto, verifique os valores");
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<GenericResponse<AddressEntity>>(response,status);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<GenericResponse<AddressEntity>> update(@PathVariable Long id, @Valid @RequestBody AddressEntity address,
			BindingResult bindingResults) {
		GenericResponse<AddressEntity> response;
		HttpStatus status;
		if (!bindingResults.hasErrors() && business.isValidZipcode(address)) {
			address.setId(id);
			response = new GenericResponse<AddressEntity>(this.business.save(address));
			status = HttpStatus.OK;
		} else {
			response = new GenericResponse<AddressEntity>(address, "Endereco no formato incorreto, verifique os valores");
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<GenericResponse<AddressEntity>>(response,status);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<AddressEntity> delete(@PathVariable Long id) {
		this.business.delete(new AddressEntity(id));
		return new ResponseEntity<AddressEntity>(HttpStatus.OK);
	}
}
