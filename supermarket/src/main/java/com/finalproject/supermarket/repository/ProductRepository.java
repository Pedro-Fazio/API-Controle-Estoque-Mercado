package com.finalproject.supermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.supermarket.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByName(String productName);
	List<Product> findBySalesAmount(Integer saleAmount);
}
