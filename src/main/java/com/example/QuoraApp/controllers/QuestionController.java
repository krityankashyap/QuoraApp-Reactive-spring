package com.example.QuoraApp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.QuoraApp.DTOs.QuestionRequestDTO;
import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.services.IQuestionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

  @GetMapping("/search")
  public Flux<QuestionResponseDTO> searchQuestion(@RequestParam String query, 
    @RequestParam(defaultValue = "0") int offset, 
    @RequestParam(defaultValue = "10") int page) {
    return questionService.searchQuestion(query, offset, page)
                          .doOnError(error -> System.out.println("Error searching questions: " + error.getMessage()))
                          .doOnComplete(() -> System.out.println("Search completed successfully"));
  }
  

}
