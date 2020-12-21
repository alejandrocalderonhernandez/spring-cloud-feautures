package com.example.authenticator.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.authenticator.model.OauthModel;
import com.example.authenticator.util.Authenticator;

@Service
public class ILoadInfo implements LoadInfo {

	@Autowired
	private Authenticator authenticator;
	
	private ResponseEntity<OauthModel> responseAuth;
	
	private static final Logger log = LoggerFactory.getLogger(ILoadInfo.class);
	
	@PostConstruct
	public void init() {
		this.responseAuth = this.authenticator.getAuth();
		if(this.responseAuth.getStatusCode().isError()) {
			log.error("Error to get token");
		} else {
			log.info("Get token: {}", this.responseAuth.getBody().toString());
		}
	}

	@Override
	public void load() {
		
	}
	
}
