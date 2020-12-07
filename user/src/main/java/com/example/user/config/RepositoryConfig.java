package com.example.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.example.user.entity.Role;
import com.example.user.entity.User;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
	
	@SuppressWarnings("rawtypes")
	private static final Class[] CLASSES = {User.class, Role.class};

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(CLASSES);
	}
	
}
