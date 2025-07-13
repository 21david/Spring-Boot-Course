package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component  // Marks this class as a Spring Bean, and makes available for Dependency Injection
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructor: CricketCoach.CricketCoach");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes! 🎳";
    }
}
