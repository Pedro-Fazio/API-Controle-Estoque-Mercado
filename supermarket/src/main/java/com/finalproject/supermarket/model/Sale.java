package com.finalproject.supermarket.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.finalproject.supermarket.controller.form.SaleProductForm;
import com.finalproject.supermarket.repository.ProductRepository;

@Entity
@Table(name="sale")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToMany(mappedBy = "sale")
	private List<SaleProduct> saleProducts;
	
	@Column(name="date")
	private LocalDateTime date = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_type")
	private PaymentType paymentType = PaymentType.CASH;
	
	public Sale() {
	}
	
	public Sale(List<SaleProductForm> saleProductsForm, ProductRepository productRepository) {
		List<SaleProduct> saleProducts = saleProductsForm.stream()
				.map(saleProductForm -> 
				saleProductForm.convert(saleProductForm, this, productRepository)).toList();
		
		this.saleProducts = saleProducts;
	}

	public Long getId() {
		return id;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<SaleProduct> getSaleProducts() {
		return saleProducts;
	}
	
	public void setSaleProducts(List<SaleProduct> saleProducts) {
		this.saleProducts = saleProducts;
	}
	
	public PaymentType getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
