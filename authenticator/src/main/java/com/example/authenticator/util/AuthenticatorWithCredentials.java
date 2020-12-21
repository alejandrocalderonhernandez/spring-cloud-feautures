package com.example.authenticator.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.authenticator.model.OauthModel;

@Component
public class AuthenticatorWithCredentials implements Authenticator {

	@Autowired
	private RestTemplate client;
	@Value("${auth.url.token}")
	private String urlToken;
	@Value("${auth.client.id}")
	private String clientId;
	@Value("${auth.client.secret}")
	private String clientSecret;
	@Value("${auth.user.username}")
	private String username;
	@Value("${auth.user.password}")
	private String password;
	@Value("${auth.types.credentials}")
	private String gratType;
	
	@Override
	public ResponseEntity<OauthModel> getAuth() {
		URI uri =this.buildUrl();
		HttpHeaders headers = this.buildHeaders();
		HttpEntity<String> request = new HttpEntity<>(headers);
		return this.client.exchange(uri, HttpMethod.POST, request, OauthModel.class);
	}
	
	private URI buildUrl() {
		try {
			StringBuilder urlBuilder = new StringBuilder(this.urlToken);
			urlBuilder.append(RequestUtil.JWT_URL_USERNAME);
			urlBuilder.append(URLEncoder.encode(this.username,StandardCharsets.UTF_8.toString()));
			urlBuilder.append(RequestUtil.JWT_URL_PASSWORD);
			urlBuilder.append(URLEncoder.encode(this.password,StandardCharsets.UTF_8.toString()));
			urlBuilder.append(RequestUtil.JWT_URL_GRAT_TYPE);
			urlBuilder.append(URLEncoder.encode(this.gratType,StandardCharsets.UTF_8.toString()));
			URI uri = URI.create(urlBuilder.toString());
			return uri;
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		StringBuilder credentials = new StringBuilder()
									.append(this.clientId)
									.append(":")
									.append(this.clientSecret);
		byte[] plainCredsBytes = credentials.toString().getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add(RequestUtil.BASIC_AUTH_HEADER_NAME, RequestUtil.BASIC_AUTH_TYPE + base64Creds);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}

}
