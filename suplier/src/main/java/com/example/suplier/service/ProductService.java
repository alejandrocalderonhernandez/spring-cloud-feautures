package com.example.suplier.service;

import org.springframework.stereotype.Service;

import com.example.suplier.model.Product;
import com.example.suplier.util.Database;

@Service
public class ProductService implements ISimpleService {

	@Override
	public Product getByName(String name) {
		Product response = new Product(); 
		Database.listOfProducts.forEach(product -> {
			if(product.getName().equals(name)) {
				response.setName(product.getName());
				response.setPrice(product.getPrice());
				response.setAvible(product.isAvible());
			}
		});
		return response;
	}

}
