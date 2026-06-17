package com.example.QuoraApp.models;

import org.springframework.stereotype.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Question {
  
  @Id
  private String id;

  @NonNull
  private String authorName;

  @NonNull
  private String questionText;
}
