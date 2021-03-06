package br.com.cidade.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.cidade.bean.AddressBean;
import br.com.cidade.bean.ZipcodeBean;

@Component
public class MockAddressRepository implements AddressRepository {
	
	Map<String,AddressBean> data;
	
	public MockAddressRepository(){
		data = new HashMap<>();
		
		AddressBean address1 = new AddressBean();
		address1.setCity("Mogi das Cruzes");
		address1.setNeighborhood("Centro");
		address1.setState("SP");
		address1.setStreet("Rua Hamilton da Silva e Costa");
		address1.setZipcode("08773150");
		data.put(address1.getZipcode(), address1);
		
		AddressBean address2 = new AddressBean();
		address2.setCity("São Paulo");
		address2.setNeighborhood("Bela Vista");
		address2.setState("SP");
		address2.setStreet("Avenida Paulista");
		address2.setZipcode("01311000");
		data.put(address2.getZipcode(), address2);
		
		AddressBean address3 = new AddressBean();
		address3.setCity("Guarulhos");
		address3.setNeighborhood("Jardim Eusonia");
		address3.setState("SP");
		address3.setStreet("Rua Joana");
		address3.setZipcode("07050300");
		data.put(address3.getZipcode(), address3);
		
		
		AddressBean address4 = new AddressBean();
		address4.setCity("Mogi das Cruzes");
		address4.setNeighborhood("Vila Mogilar");
		address4.setState("SP");
		address4.setStreet("Rua Joaquina Maria de Jesus");
		address4.setZipcode("08773350");
		data.put(address4.getZipcode(), address4);
		
		AddressBean address5 = new AddressBean();
		address5.setCity("Campinas");
		address5.setNeighborhood("Jardim Yeda");
		address5.setState("SP");
		address5.setStreet("Rua Conselho das Sociedades de Bairro");
		address5.setZipcode("13060647");
		data.put(address5.getZipcode(), address5);
	}

	@Override
	public AddressBean findByZipCode(ZipcodeBean zipcode) {
		return data.get(zipcode.getValue());
	}

}
