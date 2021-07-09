package com.finalproject.supermarket.controller.form;

import java.util.List;

import com.finalproject.supermarket.model.Sale;
import com.finalproject.supermarket.repository.ProductRepository;

public class SaleForm {
	
	private List<SaleProductForm> saleProductsForm;

	public List<SaleProductForm> getSaleProductsForm() {
		return saleProductsForm;
	}

	public void setProducts(List<SaleProductForm> saleProductsForm) {
		this.saleProductsForm = saleProductsForm;
	}

	public Sale convert(ProductRepository productRepository) {
		return new Sale(saleProductsForm, productRepository);
	}
}
