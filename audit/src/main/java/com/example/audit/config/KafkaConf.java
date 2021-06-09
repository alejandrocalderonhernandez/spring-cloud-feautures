package com.example.audit.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@Configuration
public class KafkaConf {

  @Bean
  public ConsumerFactory<String, Object> consumerFactory(){
	  return new DefaultKafkaConsumerFactory<>(this.getConsumerProps());
  }
	
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
	  ConcurrentKafkaListenerContainerFactory<String, String> listener = new ConcurrentKafkaListenerContainerFactory<>();
	  listener.setConsumerFactory(this.consumerFactory());
	  return listener;
  }
  
  private Map<String, Object> getConsumerProps() {
	  Map<String, Object> props = new HashMap<>();
	  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhos:9092");
	  props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-topic-test");
	  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	  return props;
  }
}
