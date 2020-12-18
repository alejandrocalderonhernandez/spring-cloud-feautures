package com.example.oauth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import com.example.oauth.model.User;
import com.example.oauth.rest.UserClient;

@Service
@Primary
public class TokenEnhancerImpl implements TokenEnhancer {

	@Autowired
	private UserClient client;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = client.getUserByUsername(authentication.getName());
		Map<String, Object> payload  = new HashMap<>();
		payload.put("name", user.getName());
		payload.put("lastname", user.getLastName());
		payload.put("email", user.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(payload);
		return accessToken;
	}

}
