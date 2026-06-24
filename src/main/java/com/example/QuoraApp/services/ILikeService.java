package com.example.QuoraApp.services;

import com.example.QuoraApp.DTOs.LikeRequestDTO;
import com.example.QuoraApp.DTOs.LikeResponseDTO;

import reactor.core.publisher.Mono;

public interface ILikeService {

  Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
  Mono<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, String targetType);
  Mono<LikeResponseDTO> countDisLikeByTargetIdAndTargetType(String targetId, String targetType);

  Mono<LikeResponseDTO> toggleLike(String targetId, String targetType, boolean isLiked);

}
