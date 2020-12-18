package com.example.oauth.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizatonServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private BCryptPasswordEncoder encoder;
	private AuthenticationManager authenticationManager;
	private TokenStore tokenStore;
	private JwtAccessTokenConverter jwt;
	private TokenEnhancer tokenEnhancer;
	
	@Autowired
	public AuthorizatonServerConfig(BCryptPasswordEncoder encoder,
			                        AuthenticationManager authenticationManager,
			                        TokenStore tokenStore,
			                        JwtAccessTokenConverter jwt,
			                        TokenEnhancer tokenEnhancer) {
		this.encoder = encoder;
		this.authenticationManager = authenticationManager;
		this.tokenStore = tokenStore;
		this.jwt = jwt;
		this.tokenEnhancer = tokenEnhancer;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.
			inMemory()
			.withClient("client_test")
			.secret(encoder.encode("cl13nt_s3cr3t"))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(3600)
			.refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain =  new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(this.jwt, this.tokenEnhancer));
		endpoints
		    .tokenEnhancer(tokenEnhancerChain)
			.authenticationManager(this.authenticationManager)
			.tokenStore(this.tokenStore)
			.accessTokenConverter(this.jwt);
	}
	
}
