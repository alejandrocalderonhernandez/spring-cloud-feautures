package com.example.authenticator.util;

public class RequestUtil {

	public static final String JWT_URL_PASSWORD;
	public static final String JWT_URL_USERNAME;
	public static final String JWT_URL_GRAT_TYPE;
	public static final String BASIC_AUTH_HEADER_NAME;
	public static final String BASIC_AUTH_TYPE;
	
	static {
		JWT_URL_USERNAME  = "?username=";
		JWT_URL_PASSWORD = "&password=";
		JWT_URL_GRAT_TYPE = "&grant_type=";
		BASIC_AUTH_HEADER_NAME = "Authorization";
		BASIC_AUTH_TYPE = "Basic ";
	}
}
