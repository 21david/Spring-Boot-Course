package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	// This will execute after spring beans are loaded
	public CommandLineRunner commandLineRunner(String[] args) {
		System.out.println("CruddemoApplication.commandLineRunner");
		return runner -> {
			System.out.println("Hello World!");
		};
	}
}
