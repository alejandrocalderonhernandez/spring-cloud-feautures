package com.example.authenticator.util;

public class SystemUtil {

	public static final String TOKEN_PROPERTY_NAME;
	public static final String REFRESH_TOKEN_PROPERTY_NAME;
	
	static {
		TOKEN_PROPERTY_NAME = "bearerToken";
		REFRESH_TOKEN_PROPERTY_NAME = "refreshToken";
	}
	
	public static boolean existProperty(String propertyName) {
		return System.getProperty(propertyName) != null;
	}
}
