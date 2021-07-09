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
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void returnOkAndResponseBodyWhenGettingDataFromProducts() throws Exception {
		URI uri = new URI("/products");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().is(200))
		.andReturn();
	}
	
	@Test
	public void returnForbiddenWhenGettingDataFromProductsWithInvalidUri() throws Exception {
		URI uri = new URI("/notvaliduri");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isForbidden());
	}
	
	@Test
	public void returnOkAndResponseBodyWhenGettingSpecificDataFromProducts() throws Exception {
		URI uri = new URI("/products/3");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isOk())
		.andReturn();
	}
	
	@Test
	public void returnNotFoundWhenGettingSpecificDataFromProductsWithInvalidId() throws Exception {
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
				.post("/products").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"name\":\"testing\",\"description\":\"testing\",\"amount\":\"50\",\"price\":\"12.5\"}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isCreated())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
	}
	
	@Test
	public void returnForbiddenWhenPostingAJsonDataProductWithInvalidToken() throws Exception {
		String token = "123";
		String bearerToken = "Bearer" + token;
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/products").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"name\":\"testing\",\"description\":\"testing\",\"amount\":\"50\",\"price\":\"12.5\"}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers
	            		.status().isForbidden())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
	}
	
	@Test
	public void returnBadRequestWhenPostingAJsonDataProductWithValidTokenAndEmptyAttribute() throws Exception {
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
				.post("/products").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"name\":\"\",\"description\":\"\",\"amount\":\"\",\"price\":\"\"}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers
	            		.status().isBadRequest());
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
				.delete("/products/27").header("Authorization", bearerToken))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isOk());
	}
	
	@Test
	public void returnForbiddenWhenDeletingByIdWithAInvalidToken() throws Exception {
		String token = "123";
		String bearerToken = "Bearer" + token;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/products/3").header("Authorization", bearerToken))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isForbidden());
	}

}
