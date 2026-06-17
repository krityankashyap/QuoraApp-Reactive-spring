package com.example.QuoraApp.adapters;

import com.example.QuoraApp.DTOs.QuestionResponseDTO;
import com.example.QuoraApp.models.Question;

public class QuestionAdapter {
 

  public static QuestionResponseDTO toResponseDTO(Question question) {
    return QuestionResponseDTO.builder()
        .id(question.getId())
        .title(question.getTitle())
        .content(question.getContent())
        .createdAt(question.getCreatedAt())
        .build();
  }
}
