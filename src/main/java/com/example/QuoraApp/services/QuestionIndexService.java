package com.example.QuoraApp.services;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.models.QuestionElasticSearch;

import lombok.RequiredArgsConstructor;
import com.example.QuoraApp.repository.QuestionDocumentRepository;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService{
  
  private final QuestionDocumentRepository questionDocumentRepository;
  @Override
  public void createQuestionIndex(Question question) {
    QuestionElasticSearch document= QuestionElasticSearch.builder()
                                     .content(question.getContent())
                                     .id(question.getId())
                                     .title(question.getTitle())
                                     .build();

       questionDocumentRepository.save(document);                              
  }

}

