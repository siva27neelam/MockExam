package com.mockexam.MockExam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MockExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockExamApplication.class, args);
	}

}
