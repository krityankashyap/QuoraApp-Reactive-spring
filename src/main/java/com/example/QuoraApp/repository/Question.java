package com.example.QuoraApp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface Question extends ReactiveMongoRepository<Question, String> {
  
  Flux<Question> findByAuthorId(String authorId);

  Mono<Question> countByAuthorId(String authorId);
}
