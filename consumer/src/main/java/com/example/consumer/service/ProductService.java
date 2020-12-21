package com.example.consumer.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.consumer.model.Product;
import com.example.consumer.rest.ProductClient;

@Service
@RefreshScope
public class ProductService implements ISimpleService {
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductClient client;
	
    @Value("${product.desc:'Default value'}")
	private String configRepoInfo;
    
    @Value("${product.iva:0.16}")
	private Double iva;

	@Override
	public Product getProductWithIva(String name) {
		Product product = client.getByProductName(name);
		product.setPrice(this.calculateIVA(product.getPrice()));
		return product;
	}
	
	private Double calculateIVA(Double price) {
		return price + (price * this.iva);
	}

	@PostConstruct
	public void init() {
		log.warn("configRepoInfo: {}", this.configRepoInfo);
	}
	
}
