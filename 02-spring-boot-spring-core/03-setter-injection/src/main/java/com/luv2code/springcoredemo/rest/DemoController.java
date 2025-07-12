package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Define a private field for the dependency
    private Coach myCoach;

    // Define a constructor for dependency injection (constructor injection - it injects the coach)
    @Autowired  // Optional since there's only 1 constructor, but left for academic purposes
    public DemoController(Coach theCoach) {
        // Spring figures out that this is the CricketCoach since it's the only one.
        // Otherwise we would need configuration I believe
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
