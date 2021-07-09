package com.finalproject.supermarket.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.supermarket.controller.dto.ProductDto;
import com.finalproject.supermarket.controller.form.SaleForm;
import com.finalproject.supermarket.controller.form.SaleProductForm;
import com.finalproject.supermarket.model.Product;
import com.finalproject.supermarket.model.Sale;
import com.finalproject.supermarket.repository.ProductRepository;
import com.finalproject.supermarket.repository.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SaleRepository saleRepository;

	@Override
	public Sale buyProduct(SaleForm saleForm) {
		List<Integer> saleProductsValues = new ArrayList<Integer>();
		List<String> saleProductsNames = new ArrayList<String>();
		List<Product> productsByName = null;
		Sale sale = saleForm.convert(productRepository);
		
		saleRepository.save(sale);
			
		for(SaleProductForm saleProductForm : saleForm.getSaleProductsForm()) {
			saleProductsValues.add(saleProductForm.getBuyAmount());
			saleProductsNames.add(saleProductForm.getName());
		}
		
		for(int i = 0; i < saleProductsValues.size(); i++) {
			productsByName = productRepository.findByName(saleProductsNames.get(i));
			productsByName.get(0).setAmount(productsByName.get(0).getAmount() - saleProductsValues.get(i));
			productsByName.get(0).addSaleAmount(saleProductsValues.get(i));
		}
		
		return sale;	
	}

	@Override
	public List<Sale> getSales() {
		List<Sale> sales = saleRepository.findAll();
		return sales;
	}

	@Override
	public void deleteSale(Long id) {
		saleRepository.deleteById(id);
	}

	@Override
	public List<ProductDto> topSellingProducts(Long maxSize) {
		List<Product> products = productRepository.findAll();
		List<Integer> productsSaleAmounts = new ArrayList<Integer>();
		List<Product> topSellingProducts = new ArrayList<Product>();;
		
		for(Product product : products) {
			productsSaleAmounts.add(product.getSalesAmount());
		}
		
		Collections.sort(productsSaleAmounts);
		Collections.reverse(productsSaleAmounts);
		
		for(int i = 0; i < maxSize; i++) {
			topSellingProducts.addAll(productRepository
					.findBySalesAmount(productsSaleAmounts.get(i)));
		}
		
		return ProductDto.convert(topSellingProducts);
	}
}
