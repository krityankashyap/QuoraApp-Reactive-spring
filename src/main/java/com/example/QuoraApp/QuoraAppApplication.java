package com.example.QuoraApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.example.QuoraApp.repository")
public class QuoraAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoraAppApplication.class, args);
	}

}
