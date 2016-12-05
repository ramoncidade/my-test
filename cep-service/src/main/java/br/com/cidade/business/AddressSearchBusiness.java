package br.com.cidade.business;

import br.com.cidade.bean.AddressBean;
import br.com.cidade.bean.ZipcodeBean;

public interface AddressSearchBusiness {
	
	public Boolean isValidZipcode(ZipcodeBean zipcode);
	
	public AddressBean findAddressByZipcode(ZipcodeBean zipcode);

}
