package com.example.QuoraApp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.QuoraApp.DTOs.QuestionRequestDTO;
import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.services.IQuestionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

  private final IQuestionService questionService;

  @PostMapping
  public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestdto){
    return questionService.createQuestion(questionRequestdto)
                          .doOnSuccess(success -> System.out.println("Question created successfully: " + success))
                          .doOnError(error -> System.out.println("Error creating question: " + error.getMessage()));
  }

}
