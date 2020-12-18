package com.example.consumer.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.consumer.model.Product;

@FeignClient(value = "product", url = "localhost:8090/product")
public interface ProductClient {

	@GetMapping(value = "/{name}")
	public Product getByProductName(@PathVariable String name);

}
