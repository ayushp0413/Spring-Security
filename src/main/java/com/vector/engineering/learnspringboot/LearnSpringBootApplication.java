package com.vector.engineering.learnspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ReactiveAfterSaveCallback;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LearnSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringBootApplication.class, args);
		System.out.println("Spring boot application running fine");
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

}
