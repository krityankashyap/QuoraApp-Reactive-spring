package com.example.QuoraApp.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.QuestionElasticSearch;

@Repository
public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticSearch, String>{
  
  List<QuestionElasticSearch>  findByTitleContainingOrContentContaining(String title, String content);
  
} 