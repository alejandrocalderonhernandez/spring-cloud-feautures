package com.example.notifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notifier.model.Product;
import com.example.notifier.service.NotifierService;

@RestController
@RequestMapping(path = "notify")
public class NotifierController {
	
	@Autowired
	NotifierService<Product> notifierService;
	
	@PostMapping
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		return ResponseEntity.ok(notifierService.sendNofification(product));
	}

}
