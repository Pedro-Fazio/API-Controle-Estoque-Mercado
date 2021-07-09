package com.finalproject.supermarket.controller;

import java.net.URI;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SaleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void returnOkAndResponseBodyWhenGettingDataFromSales() throws Exception {
		URI uri = new URI("/buy");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().is(200))
		.andReturn();
	}
	
	@Test
	public void returnForbiddenWhenGettingDataFromSalesWithInvalidUri() throws Exception {
		URI uri = new URI("/notvaliduri");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isForbidden());
	}
	
	@Test
	public void returnOkAndResponseBodyWhenGettingTopSalesFromSales() throws Exception {
		URI uri = new URI("/buy/topsales/15");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isOk())
		.andReturn();
	}
	
	@Test
	public void returnNotFoundWhenGettingTopSalesFromSalesWithAMaxSizeBiggerThanTheAmountOfProducts() throws Exception {
		URI uri = new URI("/products/10000");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isNotFound());
	}
	
	@Test
	public void returnCreatedAndResponseBodyWhenPostingAJsonDataProductWithValidToken() throws Exception {
		String token;
		String bearerToken = "Bearer ";
		URI uri = new URI("/auth");
		String json = "{\"email\": \"pedro@email.com\", \"pwd\": \"123456\"}";
		
		MvcResult authResult = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk())
		.andReturn();
		
		JSONObject obj = new JSONObject(authResult.getResponse().getContentAsString());
		token = obj.getString("token");
		bearerToken += token;
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/buy").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"saleProductsForm\":[{\"name\":\"doritos\",\"buyAmount\":1,\"price\":9.0}]}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isCreated())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
	}
	
	@Test
	public void returnForbiddenWhenPostingAJsonDataSaleWithInvalidToken() throws Exception {
		String token = "123";
		String bearerToken = "Bearer" + token;
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/buy").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"name\":\"doritos\",\"buyAmount\":1,\"price\":9.0}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers
	            		.status().isForbidden())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
	}
	
	@Test
	public void returnOkWhenDeletingByIdWithAValidToken() throws Exception {
		String token;
		String bearerToken = "Bearer ";
		URI uri = new URI("/auth");
		String json = "{\"email\": \"pedro@email.com\", \"pwd\": \"123456\"}";
		
		MvcResult authResult = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk())
		.andReturn();
		
		JSONObject obj = new JSONObject(authResult.getResponse().getContentAsString());
		token = obj.getString("token");
		bearerToken += token;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/buy/62").header("Authorization", bearerToken))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isOk());
	}

}
