package br.com.cidade.repository;

import br.com.cidade.bean.AddressBean;
import br.com.cidade.bean.ZipcodeBean;

public interface AddressRepository {
	public AddressBean findByZipCode(ZipcodeBean zipcode);
}
