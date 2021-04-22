package com.example.suplier.service;

import com.example.suplier.model.Product;

public interface ISimpleService {
	
	public Product getByName(String name) throws NoSuchFieldException;

}
