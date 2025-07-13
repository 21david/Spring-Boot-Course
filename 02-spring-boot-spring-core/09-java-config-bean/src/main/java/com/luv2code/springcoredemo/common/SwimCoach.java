package com.luv2code.springcoredemo.common;

// Not using @Component to show java configuration way instead
public class SwimCoach implements Coach {
    public SwimCoach() {
        System.out.println("In constructor: SwimCoach.SwimCoach");
    }

    @Override
    public String getDailyWorkout() {
        return "Swim a thousand meters as a warm up. 🏊🏻";
    }
}
