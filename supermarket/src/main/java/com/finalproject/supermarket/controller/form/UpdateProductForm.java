package com.finalproject.supermarket.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.finalproject.supermarket.model.Product;
import com.finalproject.supermarket.repository.ProductRepository;

public class UpdateProductForm {
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull
	private int amount;
	
	@NotNull
	private float price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Product update(Long id, ProductRepository productRepository) {
		Product product = productRepository.getById(id);
		product.setName(this.name);
		product.setDescription(this.description);
		product.setAmount(this.amount);
		product.setPrice(this.price);
		
		return product;
	}
}
