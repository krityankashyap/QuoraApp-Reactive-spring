package com.example.QuoraApp.services;

import com.example.QuoraApp.DTOs.AnswerRequestDTO;
import com.example.QuoraApp.DTOs.AnswerResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAnswerService {
  
  Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

  Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId);
}
