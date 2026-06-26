package com.example.QuoraApp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.DTOs.QuestionRequestDTO;
import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.adapters.QuestionAdapter;
import com.example.QuoraApp.events.ViewCountEvent;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.models.QuestionElasticSearch;
import com.example.QuoraApp.producers.kafkaEventProducer;
import com.example.QuoraApp.repository.QuestionDocumentRepository;
import com.example.QuoraApp.repository.QuestionRespo;
import com.example.QuoraApp.utils.CursorUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
  
  private final IQuestionIndexService questionService;
  private final kafkaEventProducer kafkaEventProducer;
  private final QuestionDocumentRepository questionDocumentRepository;

  private final QuestionRespo questionRespo;

  @Override
  public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionsRequestDTO) {
    Question question= Question.builder()
                      .title(questionsRequestDTO.getTitle())
                      .content(questionsRequestDTO.getContent())
                      .createdAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .build();

                return questionRespo.save(question)
                       .map(savedQuestion -> {
                           questionService.createQuestionIndex(savedQuestion);  // Create an index for the saved question in Elasticsearch
                           return QuestionAdapter.toResponseDTO(savedQuestion);
                       })
                       .doOnSuccess(success-> System.out.println("Question created successfully"))
                       .doOnError(error-> System.out.println("Error creating question: " + error.getMessage()));
  }
  @Override
  public Flux<QuestionResponseDTO> searchQuestion(String searchTerms, int offset, int page) {
    return questionRespo.findByTitleOrContentContainingIgnoreCase(searchTerms, PageRequest.of(page, offset)) // Use PageRequest to handle pagination where PageRequest.of(page, offset) creates a Pageable object with the specified page number and page size (offset)
           .map(QuestionAdapter::toResponseDTO)
           .doOnError(error-> System.out.println("Error searching questions: " + error.getMessage()))
           .doOnComplete(()-> System.out.println("Search completed successfully"));
  }

  @Override
  public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
    Pageable pageable = PageRequest.of(0, size);
    
    if(!CursorUtils.isValidCursor(cursor)){
      return questionRespo.findTop10ByOrderByCreatedAtAsc()
             .take(size)
             .map(QuestionAdapter::toResponseDTO)
             .doOnError(error -> System.out.println("Error fetching questions: " + error.getMessage()))
             .doOnComplete(() -> System.out.println("fetch completed successfully"));
    } else {
      LocalDateTime cursorTimeStamp= CursorUtils.parseCursor(cursor);
        return questionRespo.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
        .map(QuestionAdapter::toResponseDTO)
        .doOnError(error-> System.out.println("Error fetching questions: " + error.getMessage()))
        .doOnComplete(() -> System.out.println("Fetch completed successfully"));
      }
    }
  
    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String questionId){
      return questionRespo.findById(questionId)
              .map(QuestionAdapter::toResponseDTO)
              .doOnError(error-> System.out.println("Error fetching question by Id: " + error.getMessage()))
              .doOnSuccess(response-> {
                System.out.println("Fetch question by Id completed successfully");
                // Create a ViewCountEvent and publish it to Kafka
                ViewCountEvent viewCountEvent= ViewCountEvent.builder()  
                                                .targetId(questionId)
                                                .targetType("question")
                                                .timestamp(LocalDateTime.now())
                                                .build();

                  kafkaEventProducer.publishViewCountEvent(viewCountEvent);    // Publish the view count event to Kafka when a question is fetched by ID                           
              });
    }

    public List<QuestionElasticSearch> searchQuestionByElasticSearch(String query){
      return questionDocumentRepository.findByTitleContainingOrContentContaining(query, query);
    }
  }


