package com.example.QuoraApp.models;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

public class Likes {

 @Id
 private String Id;

 private String targetId; // ID of the target being liked (e.g., question ID, answer ID, etc.)

 private String targetType;  // what is being liked (e.g., "question", "answer", etc.)

 private boolean isliked;  // true if liked, false if unliked
 
 @CreatedDate
 private LocalDate createdAt;  
}
