package com.finalproject.supermarket.controller.form;

import java.util.List;

import com.finalproject.supermarket.model.Product;
import com.finalproject.supermarket.model.Sale;
import com.finalproject.supermarket.model.SaleProduct;
import com.finalproject.supermarket.repository.ProductRepository;

public class SaleProductForm {
	
	private String name;
	
	private int buyAmount;
	
	private float price;

	public SaleProductForm(Long id, String name, int buyAmount, float price) {
		this.name = name;
		this.buyAmount = buyAmount;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public SaleProduct convert(SaleProductForm saleProductForm, Sale sale, ProductRepository productRepository) {		
		List<Product> products = productRepository.findByName(name);
		Product product = products.get(0);
		
		return new SaleProduct(name, buyAmount, price, sale, product);
	}
}
