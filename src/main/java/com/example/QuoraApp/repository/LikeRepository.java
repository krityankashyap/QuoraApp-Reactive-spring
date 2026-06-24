package com.example.QuoraApp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.QuoraApp.models.Likes;

public interface LikeRepository extends ReactiveMongoRepository<Likes, String>{

}
