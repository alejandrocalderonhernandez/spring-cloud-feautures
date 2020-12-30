package com.example.oauth.service;

import com.example.oauth.model.User;

public interface IUserService {

	public User updateUser(User user, Long id);
	public User getByUsername(String username);
}
