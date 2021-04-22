package com.example.suplier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.suplier.model.Product;
import com.example.suplier.repocitory.ProductRepocitory;

@Service
public class ProductService implements ISimpleService {

	@Autowired
	ProductRepocitory repocitory;
	@Override
	
	public Product getByName(String name) throws NoSuchFieldException {
		return repocitory.findById(name).orElseThrow(() -> new NoSuchFieldException());
	}

}
