package com.finalproject.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.supermarket.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
}
