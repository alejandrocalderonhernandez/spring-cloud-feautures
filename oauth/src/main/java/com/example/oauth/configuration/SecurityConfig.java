package com.example.oauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	private UserDetailsService service;
	private BCryptPasswordEncoder encoder;
	private AuthenticationEventPublisher publisher;

	@Autowired
	public SecurityConfig(UserDetailsService service, 
			              BCryptPasswordEncoder encoder,
			              AuthenticationEventPublisher publisher) {
		this.service = service;
		this.encoder = encoder;
		this.publisher = publisher;
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(service)
		.passwordEncoder(encoder)
		.and()
		.authenticationEventPublisher(publisher);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
