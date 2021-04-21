package com.example.consumer.rest;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.consumer.model.Product;

@FeignClient(name = "notifier", path = "notify")
public interface NotifierClient {

	@PostMapping()
	public Product sendNotification(@RequestBody Product product);
}
