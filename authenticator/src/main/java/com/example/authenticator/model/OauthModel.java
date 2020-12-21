package com.example.authenticator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class OauthModel {
	
	@JsonProperty("access_token")
	private String accesToken;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private Long expirate;
	
	public OauthModel() {}

	public String getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getExpirate() {
		return expirate;
	}

	public void setExpirate(Long expirate) {
		this.expirate = expirate;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this).toString();
	}
	
	

}
