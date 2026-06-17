package com.example.QuoraApp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.Question;



@Repository
public interface QuestionRespo extends ReactiveMongoRepository<Question, String> {
  
}
