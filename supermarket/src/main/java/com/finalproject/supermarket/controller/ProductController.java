package com.finalproject.supermarket.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.finalproject.supermarket.controller.dto.ProductDto;
import com.finalproject.supermarket.controller.form.ProductForm;
import com.finalproject.supermarket.controller.form.UpdateProductForm;
import com.finalproject.supermarket.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDto>> getProducts(String productName) {
		return ResponseEntity.ok().body(productService.getProducts(productName));
	}
	
	@PostMapping
	public ResponseEntity<ProductDto> postProduct(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriBuilder) {
		URI uri = productService.buildUri(productForm, uriBuilder);
		return ResponseEntity.created(uri).body(productService.postProduct(productForm));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> filter(@PathVariable Long id) {
		if(productService.filter(id) != null) {
			return ResponseEntity.ok().body(productService.filter(id));
		} else {
			return ResponseEntity.notFound().build(); 
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> putProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductForm updateProductForm) {
		if(productService.putProduct(id, updateProductForm) != null) {
			return ResponseEntity.ok().body(productService.putProduct(id, updateProductForm));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}
}
