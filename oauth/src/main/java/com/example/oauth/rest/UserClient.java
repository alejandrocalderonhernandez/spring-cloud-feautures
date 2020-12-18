package com.example.oauth.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.oauth.model.User;

@FeignClient(value = "user", url = "localhost:9870/user")
public interface UserClient {
	
	@GetMapping(path = "/search/username")
	public User getUserByUsername(@RequestParam String username);
}
