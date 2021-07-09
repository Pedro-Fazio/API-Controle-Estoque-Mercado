package com.finalproject.supermarket.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sale_product")
public class SaleProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	@Column(name="buy_amount")
	private int buyAmount;
	
	private Float price;
	
	public SaleProduct() {
	}
	
	public SaleProduct(String name, int buyAmount, Float price, Sale sale, Product product) {
		this.name = name;
		this.buyAmount = buyAmount;
		this.price = price;
		this.sale = sale;
		this.product = product;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getBuyAmount() {
		return buyAmount;
	}
	
	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
}
