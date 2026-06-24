package com.example.QuoraApp.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.config.KafkaConfig;
import com.example.QuoraApp.events.ViewCountEvent;
import com.example.QuoraApp.repository.QuestionRespo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

  private final QuestionRespo questionRespo;
  
  @KafkaListener(
    topics = KafkaConfig.TOPIC_NAME,
    groupId = "${kafka.group-id:count-view-question}",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void handleViewCountEvent(ViewCountEvent viewCountEvent) {
      questionRespo.findById(viewCountEvent.getTargetId())
      .flatMap(question -> {
        Integer views = question.getViews();
        if (views == null) {
          views = 0;
        }
        question.setViews(views + 1);
        return questionRespo.save(question);
      })
      .subscribe(updatedQuestion -> {
        System.out.println("Updated view count for question with ID: " + updatedQuestion.getId());
      }, error -> {
        System.out.println("Error updating view count for question with ID: " + viewCountEvent.getTargetId());
      });
  }

}
