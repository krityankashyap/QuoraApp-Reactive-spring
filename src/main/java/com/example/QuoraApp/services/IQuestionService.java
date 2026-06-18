package com.example.QuoraApp.services;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.DTOs.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IQuestionService {

  public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionsRequestDTO);

  public Flux<QuestionResponseDTO> searchQuestion(String searchTerms, int offset, int page);
}
