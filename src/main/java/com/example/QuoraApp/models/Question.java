package com.example.QuoraApp.models;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questions")
public class Question {
  
  @Id
  private String id;

  @NotBlank(message = "Title cannot be blank")
  @Size(min=10, max=100, message = "Title must be between 10 and 100 characters")
  private String title;

  @NotBlank(message = "Content cannot be blank")
  @Size(min=20, message = "Content must be at least 20 characters long")
  private String content;

  @CreatedDate
  private LocalDate createdAt;

  @LastModifiedDate
  private LocalDate updatedAt;
}
