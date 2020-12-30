package com.example.oauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.oauth.model.User;
import com.example.oauth.service.IUserService;

import feign.FeignException;

@Component
@Primary
public class AuthSuccesErrorHandler implements AuthenticationEventPublisher {
	
	@Autowired
	IUserService service;

	private static final Logger log = LoggerFactory.getLogger(AuthSuccesErrorHandler.class);

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		log.info("Login: {}", this.getUserName(authentication));
		log.trace("Login: {}", this.getUserName(authentication));
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		try {
			User user = service.getByUsername(this.getUserName(authentication));
			user.setTries(user.getTries() + 1);
			if(user.getTries() == 3) {
				log.warn("User bloqued!!! {}", user);
				user.setEnabled(false);
			}
			this.service.updateUser(user, user.getId());
		} catch (FeignException e) {
			log.warn("Client not exist", exception);
		}
		log.error("Error on login", exception);
		log.trace("Error on login", exception);
	}
	
	private String getUserName(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		return user.getUsername();
	}

}
