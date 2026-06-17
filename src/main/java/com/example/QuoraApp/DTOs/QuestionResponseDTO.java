package com.example.QuoraApp.DTOs;

import java.time.LocalDate;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {
 
  private String id;

  private String title;

  private String content;

  private LocalDate createdAt;
}
