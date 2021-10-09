package com.baytree_mentoring.baytree_mentoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.baytree_mentoring.baytree_mentoring.repositories")
@SpringBootApplication
public class BaytreeMentoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaytreeMentoringApplication.class, args);
	}

}
