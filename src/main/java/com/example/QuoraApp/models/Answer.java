package com.example.QuoraApp.models;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "answers")
public class Answer {

  @Id
  private String Id;
  
  @NotBlank(message = "Content cannot be blank")
  private String content;
  
  @Indexed   // This annotation creates an index on the questionId field for faster queries
  private String questionId;
  
  @CreatedDate
  @Indexed(direction= IndexDirection.ASCENDING)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

}
