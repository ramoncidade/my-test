package br.com.cidade.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.cidade.entity.AddressEntity;
import br.com.cidade.repository.AddressRepository;

@Component
public class AddressBusinessImpl implements AddressBusiness {
	
	private AddressRepository repository;
	@Value("${validate.zipcode.url}")
	private String validateZipcodeServiceUrl;

	@Autowired
	public AddressBusinessImpl(AddressRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<AddressEntity> findOne(AddressEntity address) {
		return this.repository.findById(address);
	}

	@Override
	public Page<AddressEntity> findAll(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	public AddressEntity save(AddressEntity address) {
		return this.repository.save(address);
	}

	@Override
	public void delete(AddressEntity address) {
		this.repository.delete(address.getId());
	}

	@Override
	public boolean isValidZipcode(AddressEntity address) {
		try {
			int responseStatus = Unirest.get(validateZipcodeServiceUrl).queryString("cep", address.getZipcode()).asJson().getStatus();
			return HttpStatus.valueOf(responseStatus).is2xxSuccessful();
		} catch (UnirestException e) {
			System.err.println("Erro validando o CEP"+e.getLocalizedMessage());
			return false;
		}
	}
}
