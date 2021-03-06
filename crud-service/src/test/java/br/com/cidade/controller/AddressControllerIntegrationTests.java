package br.com.cidade.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.cidade.entity.AddressEntity;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AddressControllerIntegrationTests {
	
	@Autowired
	MockMvc mvc;

	Gson gson;
	
	@Before
	public void setup(){
		gson = new GsonBuilder().serializeNulls().create();
	}
	
	@Test
	public void shouldSaveAddress() throws Exception {
		AddressEntity address = new AddressEntity();
		address.setCity("Mogi das Cruzes");
		address.setComplement("Casa 1");
		address.setNeighborhood("Centro");
		address.setNumber(143);
		address.setState("SP");
		address.setStreet("Rua Hamilton da Silva e Costa");
		address.setZipcode("08773150");
		
		String responseAsString = mvc
			.perform(
					MockMvcRequestBuilders
						.post(AddressController.BASE_PATH)
						.content(gson.toJson(address))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
  			.andReturn().getResponse().getContentAsString();
		
		AddressEntity response = gson.fromJson(responseAsString, AddressEntity.class);
		Assert.notNull(response.getId());
	}
	
}
