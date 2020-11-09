package com.example.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumer.model.Product;
import com.example.consumer.rest.ProductClient;

@Service
public class ProductService implements ISimpleService {
	
	@Autowired
	ProductClient client;

	@Override
	public Product getProductWithIva(String name) {
		Product product = client.getByProductName(name);
		product.setPrice(this.calculateIVA(product.getPrice()));
		return product;
	}
	
	private Double calculateIVA(Double price) {
		return price + (price * 0.16);
	}

}
