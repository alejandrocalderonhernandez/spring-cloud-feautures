package com.example.suplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.suplier.model.Product;
import com.example.suplier.service.ISimpleService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ISimpleService service;
	
	@GetMapping(value = "/{name}")
	public ResponseEntity<?> getProduct(@PathVariable String name) throws NoSuchFieldException {
		Product response = this.service.getByName(name);
		return response.getName() == null ?
				ResponseEntity.notFound().build() :
				ResponseEntity.ok(response);
	}

}
