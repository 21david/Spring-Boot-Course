package com.udemycourse.springboot.thymeleafdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafdemoApplication.class, args);
		System.out.println("http://localhost:8080/hello");
		System.out.println("http://localhost:8080/showForm");
		System.out.println("http://localhost:8080/showStudentForm");
	}

}
