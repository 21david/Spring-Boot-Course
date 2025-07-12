package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // Expose "/" that returns "Hello World"
    // This should cause localhost:8080 to show "Hello World" when the application is run
    @GetMapping("/")
    public String sayHello() {
        return " Hello world! ";
    }

    // Expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k workout!";
    }

    // For testing
    @GetMapping("/ping")
    public String ping() {
        return "pong!";
    }
}
