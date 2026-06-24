package com.example.QuoraApp.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaConfig {
  
  @Value("${kafka.bootstrap-servers:localhost:9092}")  // Default value is localhost:9092 if not set in application.properties
  private String bootstrapServer;
  
  @Value("${kafka.group-id:count-view-question}")  
  private String groupId;

  private static final String TOPIC_NAME= "count-view-question";

  @Bean
  public ProducerFactory<String, Object> producerFactory(){
    Map<String, Object> configProcess= new HashMap<>();
    configProcess.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    configProcess.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProcess.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaProducerFactory<>(configProcess);
  }

  @Bean
  public ConsumerFactory<String, Object> consumerFactory(){
    Map<String, Object> configProcess= new HashMap<>();
    configProcess.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    configProcess.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    configProcess.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProcess.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaConsumerFactory<>(configProcess);
  }
}
