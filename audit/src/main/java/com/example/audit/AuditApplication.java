package com.example.audit;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@EnableEurekaClient
@SpringBootApplication
public class AuditApplication {
	
	private static final Logger log = LoggerFactory.getLogger(AuditApplication.class);

	@KafkaListener(topics = "my-topic-test", groupId = "test-id")
	public void listten(String message) {
		log.info("new message {}", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(AuditApplication.class, args);
	}

}
