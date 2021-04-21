package com.example.notifier.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.notifier.model.Product;

@Service
public class ProductNotifier implements NotifierService<Product> {

	
	private static final Logger log = LoggerFactory.getLogger(ProductNotifier.class);

	@Override
	public Product sendNofification(Product bodyNofification) {
		log.info("Notifing {}", bodyNofification.toString());
		return bodyNofification;
	}

}
