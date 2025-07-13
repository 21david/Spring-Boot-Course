package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component  // Marks this class as a Spring Bean, and makes available for Dependency Injection
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructor: CricketCoach.CricketCoach");
    }

    // Define init method
    @PostConstruct
    public void doStartupStuff() {  // Any name here can be chosen
        System.out.println("In method: CricketCoach.doStartupStuff");
    }

    // Define destroy method
    @PreDestroy
    public void doCleanupStuff() {  // Any name here can be chosen
        System.out.println("In method: CricketCoach.doCleanupStuff");
    }

    @Override
    public String getDailyWorkout() {
        return "(Cricket coach) Practice fast bowling for 15 minutes! 🎳";
    }
}
