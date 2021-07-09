package com.finalproject.supermarket.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.finalproject.supermarket.controller.dto.ProductDto;
import com.finalproject.supermarket.controller.form.ProductForm;
import com.finalproject.supermarket.controller.form.UpdateProductForm;
import com.finalproject.supermarket.model.Product;
import com.finalproject.supermarket.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<ProductDto> getProducts(String productName) {
		if(productName == null) {
			List<Product> products = productRepository.findAll();
			return ProductDto.convert(products);
		} else {
			List<Product> products = productRepository.findByName(productName);
			return ProductDto.convert(products);
		}
	}

	@Override
	public ProductDto postProduct(ProductForm productForm) {
		Product product = productForm.convert();
		productRepository.save(product);
		
		return new ProductDto(product);
	}

	@Override
	public ProductDto filter(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if(product.isPresent()) {
			return new ProductDto(product.get());	
		} else {
			return null;
		}
	}

	@Override
	public ProductDto putProduct(Long id, UpdateProductForm updateProductForm) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		
		if(optionalProduct.isPresent()) {		
			Product product = updateProductForm.update(id, productRepository);	
			return new ProductDto(product);
		} else {
			return null;
		}
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public URI buildUri(ProductForm productForm, UriComponentsBuilder uriBuilder) {
		Product product = productForm.convert();
		
		return uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
	}
	
}
