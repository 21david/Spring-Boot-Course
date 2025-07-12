package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // Inject properties for: coach.name and team.name from application.properties
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // Expose new endpoint for "teaminfo"
    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return String.format("Coach: %s, Team name: %s", coachName, teamName);
    }

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
