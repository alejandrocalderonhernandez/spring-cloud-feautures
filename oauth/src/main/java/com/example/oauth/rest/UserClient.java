package com.example.oauth.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.oauth.model.User;

@FeignClient(name = "user", path = "user")
public interface UserClient {
	
	@GetMapping(path = "/search/username")
	public User getUserByUsername(@RequestParam String username);
	
	@PutMapping(path = "/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id);
	
}
