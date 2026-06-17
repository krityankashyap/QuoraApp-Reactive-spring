package com.example.QuoraApp.services;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.DTOs.*;


import reactor.core.publisher.Mono;

@Service
public interface IQuestionService {

  public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionsRequestDTO);
}
