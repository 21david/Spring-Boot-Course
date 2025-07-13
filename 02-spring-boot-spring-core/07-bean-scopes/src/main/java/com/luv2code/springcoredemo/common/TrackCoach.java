package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In constructor: TrackCoach.TrackCoach");
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k! 🏃🏻‍♂️️";
    }

}

