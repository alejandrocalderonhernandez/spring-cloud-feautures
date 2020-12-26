package com.example.authenticator.service;

import java.net.URI;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.authenticator.model.OauthModel;
import com.example.authenticator.util.RequestUtil;
import com.example.authenticator.util.SystemUtil;

@Component(value = "refreshToken")
public class AuthenticatorWithRefreshToken implements Authenticator{
	
	@Autowired
	private RestTemplate client;

	@Value("${auth.url.token}")
	private String urlToken;
	@Value("${auth.client.id}")
	private String clientId;
	@Value("${auth.client.secret}")
	private String clientSecret;
	@Value("${auth.types.refresh}")
	private String gratType;

	@Override
	public OauthModel getAuth() throws AuthenticationException {
		String refreshToken = System.getProperty(SystemUtil.REFRESH_TOKEN_PROPERTY_NAME);
		URI uri = RequestUtil.buildUrl(this.gratType, this.urlToken, refreshToken);
		HttpHeaders headers = RequestUtil.buildHeaders(this.clientId, this.clientSecret);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<OauthModel> response =
				this.client.exchange(uri, HttpMethod.POST, request, OauthModel.class);
		RequestUtil.validateRequest(response);
		return response.getBody();
	}

}
