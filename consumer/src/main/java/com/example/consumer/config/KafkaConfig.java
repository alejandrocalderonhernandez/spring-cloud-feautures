package com.example.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class KafkaConfig {
  
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
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	  props.put(ProducerConfig.RETRIES_CONFIG, 0);
	  props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
	  props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  return props;
  }
}
