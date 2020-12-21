package com.example.authenticator.util;

import org.springframework.http.ResponseEntity;

import com.example.authenticator.model.OauthModel;

public interface Authenticator {
	
	public ResponseEntity<OauthModel> getAuth();

}
