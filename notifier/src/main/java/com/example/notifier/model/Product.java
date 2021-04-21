package com.example.notifier.model;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 120L;
	
	private String name;
	private Double price;
	private Boolean avible;
	
	public Product() {
	}

	
	public Product(String name, Double price, Boolean avible) {
		this.name = name;
		this.price = price;
		this.avible = avible;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public Boolean isAvible() {
		return avible;
	}

	public void setAvible(Boolean avible) {
		this.avible = avible;
	}


	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", avible=" + avible + "]";
	}

}