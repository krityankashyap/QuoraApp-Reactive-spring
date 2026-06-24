package com.example.QuoraApp.DTOs;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDTO {
  
  private String id;
  
  @NotBlank(message = "Content cannot be blank")
  @Size(min=10, max=1000, message = "Content must be between 10 and 1000 characters")
  private String content;

  @NotNull(message = "Question ID cannot be null")
  private String questionId;

  private LocalDateTime createdAt;
}
