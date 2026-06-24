package com.example.QuoraApp.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {
 
  private String targetId;
  
  @NotBlank(message = "targetValue cannot be blank")
  private String targetValue;

  private boolean isLiked;
}
