package br.com.cidade.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cidade.bean.AddressBean;
import br.com.cidade.bean.ZipcodeBean;
import br.com.cidade.repository.AddressRepository;

@Component("addressSearchBusiness")
public class AddressSearchBusinessImpl implements AddressSearchBusiness {

	private static final int RETRIES_COUNT = 8;
	private AddressRepository repository;
	
	@Autowired
	public AddressSearchBusinessImpl(AddressRepository repository){
		this.repository = repository;
	}

	@Override
	public Boolean isValidZipcode(ZipcodeBean zipcode) {
		Boolean valid = Boolean.FALSE;
		if (valid = zipcode.getValue() != null) {
			// Verificar se contém apenas números e quantidade de caracteres
			// correta
			if (valid = zipcode.getValue().length() == 8) {
				valid = zipcode.getValue().matches("[0-9]+");
			}
		}
		return valid;
	}

	@Override
	public AddressBean findAddressByZipcode(ZipcodeBean zipcode) {
		AddressBean address = null;
		boolean found = Boolean.FALSE;
		for (int triesCount = 1; triesCount <= RETRIES_COUNT; triesCount++) {
			if (!found) {
				address = repository.findByZipCode(zipcode);
				found = address != null;
				zipcode = tryNewZipcode(zipcode, triesCount);
			}else{
				break;
			}
		}
		return address;
	}
	
	private ZipcodeBean tryNewZipcode(ZipcodeBean zipcode, int tryCount){
		StringBuilder newValue = new StringBuilder(zipcode.getValue());
		for (int i = 1; i <= tryCount; i++) {
			newValue.setCharAt((zipcode.getValue().length()-i), '0');
		}
		return new ZipcodeBean(newValue.toString());
	}

}
