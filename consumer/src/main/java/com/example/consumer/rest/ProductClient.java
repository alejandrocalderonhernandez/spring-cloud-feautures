package com.example.consumer.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.consumer.model.Product;

@FeignClient(name = "product", url = "localhost:8090")
@RequestMapping("product")
public interface ProductClient {

	@GetMapping(value = "/get/{name}")
	public Product getByProductName(@PathVariable String name);

}