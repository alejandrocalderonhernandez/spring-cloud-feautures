package com.example.authenticator.service;


import java.util.NoSuchElementException;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.authenticator.model.OauthModel;
import com.example.authenticator.util.SystemUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	@Qualifier(value = "credentials")
	private Authenticator authWithCredentials;
	@Autowired
	@Qualifier(value = "refreshToken")
	private Authenticator authWithRefreshToken;
	
	private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Override
	public void authWithCredentials() {
		OauthModel response = null;
		try {
			response = this.authWithCredentials.getAuth();
			log.info("New token expires in {}", response.getExpirate());
		} catch (AuthenticationException e) {
			log.error("Error to get token with credentials", e);
		}
		System.setProperty(SystemUtil.TOKEN_PROPERTY_NAME, response.getAccesToken());
		System.setProperty(SystemUtil.REFRESH_TOKEN_PROPERTY_NAME, response.getRefreshToken());
	}


	@Override
	public void authWithRefreshToken() {
		if(!SystemUtil.existProperty(SystemUtil.REFRESH_TOKEN_PROPERTY_NAME)) {
			throw new NoSuchElementException("Refresh token was not found");
		}
		OauthModel response = null;
		try {
			response = this.authWithRefreshToken.getAuth();
			log.info("New token expires in {}", response.getExpirate());
		} catch (AuthenticationException e) {
			log.error("Error to get token with refresh token", e);
		}
		System.setProperty(SystemUtil.TOKEN_PROPERTY_NAME, response.getAccesToken());
		System.setProperty(SystemUtil.REFRESH_TOKEN_PROPERTY_NAME, response.getRefreshToken());
		
	}
	
}
