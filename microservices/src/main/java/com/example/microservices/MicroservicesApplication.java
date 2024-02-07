package com.example.microservices;

import com.example.microservices.movieservice.MovieController;
import com.example.microservices.movieservice.MovieRepository;
import com.example.microservices.movieservice.service.MovieService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}

}
