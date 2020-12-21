package com.example.authenticator.configuratiom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

import com.example.authenticator.util.YamlPropertySourceFactory;

@Configuration
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:http-client.yml", factory = YamlPropertySourceFactory.class)
public class BeanConfig {
	
	@Bean
	public PropertySourcesPlaceholderConfigurer loadProperties() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
