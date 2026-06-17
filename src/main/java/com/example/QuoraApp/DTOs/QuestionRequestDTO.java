package com.example.QuoraApp.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {
 
  @NotBlank(message = "Title cannot be blank")
  @Size(min=10, max=100, message = "Title must be between 10 and 100 characters")
  private String title;

  @NotBlank(message = "Content cannot be blank")
  @Size(min=20, message = "Content must be at least 20 characters long")
  private String content;

  
}
