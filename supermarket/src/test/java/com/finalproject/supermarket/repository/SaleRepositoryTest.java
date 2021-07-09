package com.finalproject.supermarket.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.finalproject.supermarket.model.Sale;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SaleRepositoryTest {
	
	@Autowired
	SaleRepository saleRepository;

	@Test
	public void loadAllSales() {	
		List<Sale> sales = saleRepository.findAll();
		
		Assert.assertNotNull(sales);
	}
}
