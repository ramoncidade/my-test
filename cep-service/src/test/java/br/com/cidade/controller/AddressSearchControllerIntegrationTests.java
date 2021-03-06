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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.cidade.bean.AddressBean;
import br.com.cidade.bean.utils.GenericResponse;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AddressSearchControllerIntegrationTests {
	
	@Autowired
	MockMvc mvc;

	Gson gson;
	
	@Before
	public void setup(){
		gson = new GsonBuilder().serializeNulls().create();
	}
	
	@Test
	public void shouldReturnInvalid() throws Exception {
		String invalidCep = "123456789";
		mvc
			.perform(
					MockMvcRequestBuilders
						.get(AddressSearchController.BASE_PATH)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.param("cep", invalidCep))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(gson.toJson(new GenericResponse<AddressBean>(null,"CEP inválido")), true))
			.andDo(print())
			.andReturn();
	}

	@Test
	public void shouldReturnNotFound() throws Exception {
		String inexistentCep = "55555555";
		mvc
			.perform(
					MockMvcRequestBuilders
						.get(AddressSearchController.BASE_PATH)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.param("cep", inexistentCep))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(gson.toJson(new GenericResponse<AddressBean>(null,"Endereco nao encontrado")), true))
			.andDo(print())
			.andReturn();
	}
	
	@Test
	public void shouldReturnValidAddressWithoutModifyingTheInput() throws Exception {
		String input = "08773150";
		AddressBean address = new AddressBean();
		address.setCity("Mogi das Cruzes");
		address.setNeighborhood("Centro");
		address.setState("SP");
		address.setStreet("Rua Hamilton da Silva e Costa");
		address.setZipcode("08773150");
		mvc
			.perform(
					MockMvcRequestBuilders
						.get(AddressSearchController.BASE_PATH)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.param("cep", input))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(gson.toJson(new GenericResponse<AddressBean>(address,"Endereco encontrado")), true))
			.andDo(print())
			.andReturn();
	}
	
	@Test
	public void shouldReturnValidAddressModifyingTheInput() throws Exception {
		String input = "01311111";
		
		AddressBean address = new AddressBean();
		address.setCity("São Paulo");
		address.setNeighborhood("Bela Vista");
		address.setState("SP");
		address.setStreet("Avenida Paulista");
		address.setZipcode("01311000");
		
		mvc
			.perform(
					MockMvcRequestBuilders
						.get(AddressSearchController.BASE_PATH)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.param("cep", input))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(gson.toJson(new GenericResponse<AddressBean>(address,"Endereco encontrado")), true))
			.andDo(print())
			.andReturn();
	}
	
}
