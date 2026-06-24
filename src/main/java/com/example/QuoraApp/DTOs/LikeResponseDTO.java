package com.example.QuoraApp.DTOs;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {

  private String id;
  
  private String targetId;
  
  @NotBlank(message = "targetValue cannot be blank")
  private String targetValue;

  private boolean isLiked;

  private LocalDateTime createdAt;
}
