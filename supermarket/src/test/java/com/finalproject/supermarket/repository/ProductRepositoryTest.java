package com.finalproject.supermarket.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.finalproject.supermarket.model.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void loadProductsByName() {
		String productName = "beans";
		
		List<Product> products = productRepository.findByName(productName);
		
		Assert.assertNotNull(products);
		
		for(int i = 0; i < products.size(); i++) {			
			assertEquals(productName, products.get(i).getName());
		}
	}
	
	@Test
	public void loadProductsByNoneExistentName() {
		String productName = "itsnotaproduct";
		
		List<Product> products = productRepository.findByName(productName);
		
		assertTrue(products.isEmpty());
	}

	public void loadProductsBySalesAmount() {
		Integer salesAmount = 5;
		
		List<Product> products = productRepository.findBySalesAmount(salesAmount);
		
		Assert.assertNotNull(products);
		
		for(int i = 0; i < products.size(); i++) {			
			assertEquals(salesAmount, products.get(i).getSalesAmount());
		}
	}
	
	@Test
	public void loadProductsByNoneExistentSaleAmount() {
		Integer salesAmount = 999999;
		
		List<Product> products = productRepository.findBySalesAmount(salesAmount);
		
		assertTrue(products.isEmpty());
	}
}
