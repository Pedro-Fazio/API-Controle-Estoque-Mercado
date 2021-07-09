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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.finalproject.supermarket.controller.dto.ProductDto;
import com.finalproject.supermarket.controller.form.SaleForm;
import com.finalproject.supermarket.model.Sale;
import com.finalproject.supermarket.repository.ProductRepository;
import com.finalproject.supermarket.repository.SaleRepository;
import com.finalproject.supermarket.service.SaleService;

@RestController
@RequestMapping("/buy")
public class SaleController {
	
	@Autowired
	SaleService saleService;
	
	@Autowired
	SaleRepository saleRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping()
	@Transactional
	public ResponseEntity<Sale> buyProduct(@RequestBody @Valid SaleForm saleForm, UriComponentsBuilder uriComponentBuilder) {		
		Sale sale = saleForm.convert(productRepository);
		
		URI uri = uriComponentBuilder.path("/sale/{id}").buildAndExpand(sale.getId()).toUri();
		
		return ResponseEntity.created(uri).body(saleService.buyProduct(saleForm));
	}
	
	@GetMapping
	public ResponseEntity<List<Sale>> getProduct() {
		return ResponseEntity.ok().body(saleService.getSales());
	}
	
	@GetMapping("/topsales/{maxSize}")
	public ResponseEntity<List<ProductDto>> topSellingProducts(@PathVariable Long maxSize) {
		return ResponseEntity.ok().body(saleService.topSellingProducts(maxSize));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSale(@PathVariable Long id) {
		saleService.deleteSale(id);
		return ResponseEntity.ok().build();
	}
}
