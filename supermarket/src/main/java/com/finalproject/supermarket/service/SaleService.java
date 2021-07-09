package com.finalproject.supermarket.service;

import java.util.List;

import com.finalproject.supermarket.controller.dto.ProductDto;
import com.finalproject.supermarket.controller.form.SaleForm;
import com.finalproject.supermarket.model.Sale;

public interface SaleService {
	public abstract Sale buyProduct(SaleForm saleForm);
	public abstract List<Sale> getSales();
	public abstract void deleteSale(Long id);
	public abstract List<ProductDto> topSellingProducts(Long maxSize);
}
