package com.example.authenticator.service;

import javax.security.sasl.AuthenticationException;

import com.example.authenticator.model.OauthModel;

public interface Authenticator {
	
	public OauthModel getAuth() throws AuthenticationException;

}
