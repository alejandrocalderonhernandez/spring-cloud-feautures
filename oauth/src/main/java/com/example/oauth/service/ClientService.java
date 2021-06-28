package com.example.oauth.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ClientService implements ClientDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		BaseClientDetails base = new BaseClientDetails();
		base.setClientId("client_test");
		base.setClientSecret(encoder.encode("cl13nt_s3cr3t"));
		base.setScope(Arrays.asList("read", "write"));
		base.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));
		base.setAccessTokenValiditySeconds(3600);
		base.setRefreshTokenValiditySeconds(7200);
		return base;
	}

}
