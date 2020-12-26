package com.example.authenticator.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.security.sasl.AuthenticationException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.authenticator.model.OauthModel;

public class RequestUtil {

	public static final String JWT_URL_PASSWORD;
	public static final String JWT_URL_USERNAME;
	public static final String JWT_URL_REFRESH_TOKEN;
	public static final String JWT_URL_GRAT_TYPE;
	public static final String BASIC_AUTH_HEADER_NAME;
	public static final String BASIC_AUTH_TYPE;
	
	static {
		JWT_URL_USERNAME  = "?username=";
		JWT_URL_PASSWORD = "&password=";
		JWT_URL_GRAT_TYPE = "&grant_type=";
		JWT_URL_REFRESH_TOKEN = "?refresh_token=";
		BASIC_AUTH_HEADER_NAME = "Authorization";
		BASIC_AUTH_TYPE = "Basic ";
	}
	
	private static final Logger log = LoggerFactory.getLogger(RequestUtil.class);
	
	public static void validateRequest(final ResponseEntity<OauthModel> response) throws AuthenticationException {
		if(response.getStatusCode().isError()) {
			log.error("Error to get token");
			throw new AuthenticationException(response.getStatusCode().toString());
		} else {
			log.info("Get token: {}", response.getBody().toString());
		}
	}
	
	public static URI buildUrl(final String grantType, final String urlToken,
			final String username, final String password) throws AuthenticationException {
		try {
			StringBuilder urlBuilder = new StringBuilder(urlToken);
			urlBuilder.append(RequestUtil.JWT_URL_USERNAME);
			urlBuilder.append(URLEncoder.encode(username,StandardCharsets.UTF_8.toString()));
			urlBuilder.append(RequestUtil.JWT_URL_PASSWORD);
			urlBuilder.append(URLEncoder.encode(password,StandardCharsets.UTF_8.toString()));
			urlBuilder.append(RequestUtil.JWT_URL_GRAT_TYPE);
			urlBuilder.append(URLEncoder.encode(grantType,StandardCharsets.UTF_8.toString()));
			URI uri = URI.create(urlBuilder.toString());
			return uri;
		} catch (UnsupportedEncodingException e) {
			log.error("Error to build url", e);
			throw new AuthenticationException("Error to build url" + e.getMessage());
		}
	}
	
	public static URI buildUrl(final String grantType, final String urlToken,
			final String refreshToken) throws AuthenticationException {
		try {
			StringBuilder urlBuilder = new StringBuilder(urlToken);
			urlBuilder.append(RequestUtil.JWT_URL_REFRESH_TOKEN);
			urlBuilder.append(URLEncoder.encode(refreshToken,StandardCharsets.UTF_8.toString()));
			urlBuilder.append(RequestUtil.JWT_URL_GRAT_TYPE);
			urlBuilder.append(URLEncoder.encode(grantType,StandardCharsets.UTF_8.toString()));
			URI uri = URI.create(urlBuilder.toString());
			return uri;
		} catch (UnsupportedEncodingException e) {
			log.error("Error to build url", e);
			throw new AuthenticationException("Error to build url" + e.getMessage());
		}
	}
	
	public static HttpHeaders buildHeaders(final String clientId, final String clientSecret) {
		HttpHeaders headers = new HttpHeaders();
		StringBuilder credentials = new StringBuilder()
									.append(clientId)
									.append(":")
									.append(clientSecret);
		byte[] plainCredsBytes = credentials.toString().getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers.add(BASIC_AUTH_HEADER_NAME, BASIC_AUTH_TYPE + base64Creds);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}
}
