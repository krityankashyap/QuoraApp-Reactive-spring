package com.example.QuoraApp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.QuoraApp.DTOs.QuestionRequestDTO;
import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.models.QuestionElasticSearch;
import com.example.QuoraApp.services.IQuestionIndexService;
import com.example.QuoraApp.services.IQuestionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
  
  @GetMapping()
  public Flux<QuestionResponseDTO> getAllQuestion(
    @RequestParam(required = false) String cursor,
    @RequestParam(defaultValue = "10") int size
  ) {
     return questionService.getAllQuestions(cursor, size)
                           .doOnError(error -> System.out.println("Error fetching questions: " + error.getMessage()))
                           .doOnComplete(() -> System.out.println("Fetch completed successfully"));
  }

  @GetMapping("/{id}")
  public Mono<QuestionResponseDTO> getQuestionById(@RequestParam String id){
    return questionService.getQuestionById(id)
                          .doOnError(error -> System.out.println("Error fetching question by ID: " + error.getMessage()))
                          .doOnSuccess(success -> System.out.println("Question fetched successfully: " + success));
  }

  @GetMapping("/elasticSearch")
  public List<QuestionElasticSearch> getAllQuestionsFromElasticSearch(@RequestParam String query) {
    return questionService.searchQuestionByElasticSearch(query);
  }
  
}
