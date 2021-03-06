package br.com.cidade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller para consulta de endereço por CEP
 * Como retorno é a entidade Address (Endereço) e não o CEP, nomeei o recurso como address
 * e vou tratar o CEP apenas como filtro de busca 
 * @author ramoncidade
 *
 */

import br.com.cidade.bean.AddressBean;
import br.com.cidade.bean.ZipcodeBean;
import br.com.cidade.bean.utils.GenericResponse;
import br.com.cidade.business.AddressSearchBusiness;

@RestController
@RequestMapping(path = AddressSearchController.BASE_PATH, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AddressSearchController {

	static final String BASE_PATH = "/rest/address";
	
	private static final String CEP_INVALIDO = "CEP inválido";
	private static final String ADDRESS_NOT_FOUND = "Endereco nao encontrado";
	private static final String ADDRESS_FOUND = "Endereco encontrado";

	
	AddressSearchBusiness business;

	@Autowired 
	public AddressSearchController(AddressSearchBusiness business) {
		this.business = business;
	}

	/**
	 * Endpoint para busca de um endereço a partir de um CEP válido
	 * @return 
	 */
	@GetMapping
	public ResponseEntity<GenericResponse<AddressBean>> findAddressByCEP(@RequestParam(name="cep",required=true)String cep){
		AddressBean address = null;
		HttpStatus status = null;
		GenericResponse<AddressBean> response = null;
		ZipcodeBean zipcode = new ZipcodeBean(cep);
		if(business.isValidZipcode(zipcode)){
			address = this.business.findAddressByZipcode(zipcode);
			if(address != null){
				response = new GenericResponse<AddressBean>(address,ADDRESS_FOUND);
				status = HttpStatus.OK;
			}else{
				response = new GenericResponse<AddressBean>(address,ADDRESS_NOT_FOUND);
				status = HttpStatus.NOT_FOUND;
			}
		}else{
			response = new GenericResponse<AddressBean>(address,CEP_INVALIDO);
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<GenericResponse<AddressBean>>(response,status);
	}
}
