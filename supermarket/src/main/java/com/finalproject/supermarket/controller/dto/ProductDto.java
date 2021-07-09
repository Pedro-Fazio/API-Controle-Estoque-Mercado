package com.finalproject.supermarket.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.finalproject.supermarket.model.Product;

public class ProductDto {
	
	private Long id;
	private String name;
	private int amount;
	private float price;
	private int salesAmount;
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.amount = product.getAmount();
		this.price = product.getPrice();
		this.salesAmount = product.getSalesAmount();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public float getPrice() {
		return price;
	}

	public int getSalesAmount() {
		return salesAmount;
	}

	public static List<ProductDto> convert(List<Product> products) {	
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}
}
