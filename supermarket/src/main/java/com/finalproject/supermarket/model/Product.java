package com.finalproject.supermarket.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="price")
	private float price;
	
	@OneToMany
	private List<SaleProduct> salesProducts;
	
	@Column(name="sales_amount")
	private int salesAmount = 0;
	
	public Product() {
	}
	
	public Product(String name, String description, int amount, float price) {
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.price = price;
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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

	public List<SaleProduct> getSalesProducts() {
		return salesProducts;
	}

	public void setSalesProducts(List<SaleProduct> salesProducts) {
		this.salesProducts = salesProducts;
	}
	
	public int getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}

	public void addSaleAmount(int saleAmount) {
		this.salesAmount += saleAmount;
	}
}
