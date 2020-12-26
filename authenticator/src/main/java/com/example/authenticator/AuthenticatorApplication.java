package com.example.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.example.authenticator.service.AuthService;

@SpringBootApplication
@EnableEurekaClient
public class AuthenticatorApplication implements CommandLineRunner {

	@Autowired 
	AuthService service;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.service.authWithCredentials();
		System.out.println("wating...");
		Thread.sleep(5000);
		this.service.authWithRefreshToken();
	}

}
