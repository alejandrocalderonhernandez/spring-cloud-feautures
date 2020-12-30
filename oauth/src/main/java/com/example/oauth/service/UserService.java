package com.example.oauth.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.oauth.model.Role;
import com.example.oauth.model.User;
import com.example.oauth.rest.UserClient;

@Service
@Primary
public class UserService implements UserDetailsService, IUserService{
	
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private UserClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userFromDB = this.client.getUserByUsername(username);
		if(userFromDB == null) {
			log.error("Error to get user: " + username);
			throw new UsernameNotFoundException("User not found!");
		}
		return new org.springframework.security.core.userdetails.User(
				userFromDB.getUsername(),
				userFromDB.getPassword(),
				userFromDB.isEnabled(),
				true,
				true,
				true,
				this.getAuthorities(userFromDB.getRoles()));
	}
	
	@Override
	public User updateUser(User user, Long id) {
		return this.client.updateUser(user, id);
	}
	
	@Override
	public User getByUsername(String username) {
		System.out.println("error: " + username);
		try {
			return client.getUserByUsername(username);
		} catch (Exception e) {
			System.out.println("error: " + e.toString());
		}
		return null;
	}
	
	private Set<GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return roles.stream()
			.map(role -> new SimpleGrantedAuthority(role.getName()))
			.collect(Collectors.toSet());
	}

}
