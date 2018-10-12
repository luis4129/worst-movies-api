package com.texoit.worstmovies;

import com.texoit.worstmovies.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorstMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorstMoviesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MovieService movieService) {
		return args -> {
			movieService.importData();
		};
	}
}
