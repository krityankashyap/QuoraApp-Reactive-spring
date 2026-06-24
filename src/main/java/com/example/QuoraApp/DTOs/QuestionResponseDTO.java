package com.example.QuoraApp.DTOs;


import java.time.LocalDateTime;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {
 
  private String id;

  private String title;

  private String content;

  private LocalDateTime createdAt;
}
