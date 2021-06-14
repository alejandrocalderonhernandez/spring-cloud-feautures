package com.example.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
	  
  @Value("${kafka.server.host}")
	String host;
  
  @Value("${kafka.server.port}")
	int port;
  
  @Value("${kafka.batch.size}")
	int batchSize;
  
  @Value("${kafka.retires}")
	int retires;
  
  @Value("${kafka.linger}")
	int linger;
  
  @Bean
  public ProducerFactory<String, String> producerFactory() {
      return new DefaultKafkaProducerFactory<>(this.getProducerProps());
  }
  
  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
	  return new KafkaTemplate<>(this.producerFactory());
  }
  private Map<String, Object> getProducerProps() {
	  Map<String, Object> props = new HashMap<>();
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host + ":" + port);
	  props.put(ProducerConfig.RETRIES_CONFIG, retires);
	  props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
	  props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  return props;
  }
}
