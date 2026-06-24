package com.example.QuoraApp.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.DTOs.QuestionRequestDTO;
import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.adapters.QuestionAdapter;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repository.QuestionRespo;
import com.example.QuoraApp.utils.CursorUtils;

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
                      .createdAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .build();

                return questionRespo.save(question)
                       .map(QuestionAdapter::toResponseDTO)
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

}
