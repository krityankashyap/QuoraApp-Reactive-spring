package com.example.QuoraApp.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.config.KafkaConfig;
import com.example.QuoraApp.events.ViewCountEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class kafkaEventProducer {
  
 private final KafkaTemplate<String, Object> kafkaTemplate;

 public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
  kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
  .whenComplete((result, err) -> {
    if(err!= null) {
      System.out.println("Error while sending message to Kafka: " + err.getMessage());
    } else {
      System.out.println("Message sent to Kafka successfully: " + result.getRecordMetadata().toString());
    }
  });
 }
}
