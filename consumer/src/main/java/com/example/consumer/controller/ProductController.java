package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.model.Product;
import com.example.consumer.service.ISimpleService;

@RestController
@RequestMapping("iva")
public class ProductController {

	@Autowired
	ISimpleService service;
	
	@GetMapping(value = "/get/{name}")
	public ResponseEntity<?> getProduct(@PathVariable String name) {
		Product response = null;
		try {
			 response = this.service.getProductWithIva(name);
			 return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
