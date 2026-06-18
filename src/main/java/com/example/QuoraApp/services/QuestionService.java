package com.example.QuoraApp.services;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.DTOs.QuestionRequestDTO;
import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.adapters.QuestionAdapter;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repository.QuestionRespo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
  

  private final QuestionRespo questionRespo;

  @Override
  public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionsRequestDTO) {
    Question question= Question.builder()
                      .title(questionsRequestDTO.getTitle())
                      .content(questionsRequestDTO.getContent())
                      .updatedAt(LocalDate.now())
                      .createdAt(LocalDate.now())
                      .build();

                return questionRespo.save(question)
                       .map(QuestionAdapter::toResponseDTO)
                       .doOnSuccess(success-> System.out.println("Question created successfully"))
                       .doOnError(error-> System.out.println("Error creating question: " + error.getMessage()));
  }
  @Override
  public Flux<QuestionResponseDTO> searchQuestion(String searchTerms, int offset, int page) {
    return questionRespo.findByTitleOrContentContainingIgnoreCase(searchTerms, PageRequest.of(page, offset))
           .map(QuestionAdapter::toResponseDTO)
           .doOnError(error-> System.out.println("Error searching questions: " + error.getMessage()))
           .doOnComplete(()-> System.out.println("Search completed successfully"));
  }

}
