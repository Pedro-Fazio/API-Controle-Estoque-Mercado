package com.finalproject.supermarket.service;

import java.net.URI;
import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;

import com.finalproject.supermarket.controller.dto.ProductDto;
import com.finalproject.supermarket.controller.form.ProductForm;
import com.finalproject.supermarket.controller.form.UpdateProductForm;

public interface ProductService {	
	public abstract List<ProductDto> getProducts(String productName);
	public abstract ProductDto postProduct(ProductForm productForm);
	public abstract ProductDto filter(Long id);
	public abstract ProductDto putProduct(Long id, UpdateProductForm updateProductForm);
	public abstract void deleteProduct(Long id);
	public abstract URI buildUri(ProductForm productForm, UriComponentsBuilder uriBuilder);
}
