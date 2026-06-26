package com.example.QuoraApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.DTOs.*;
import com.example.QuoraApp.models.QuestionElasticSearch;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IQuestionService {

  public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionsRequestDTO);

  public Flux<QuestionResponseDTO> searchQuestion(String searchTerms, int offset, int page);

  public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);

  public Mono<QuestionResponseDTO> getQuestionById(String questionId);

  public List<QuestionElasticSearch> searchQuestionByElasticSearch(String query);
}
