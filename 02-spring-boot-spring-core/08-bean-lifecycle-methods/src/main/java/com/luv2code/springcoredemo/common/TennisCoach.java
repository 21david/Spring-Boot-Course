package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    public TennisCoach() {
        System.out.println("In constructor: TennisCoach.TennisCoach");
    }

    @Override
    public String getDailyWorkout() {
        // IntelliJ shortcut - 'sout', 'soutv', 'soutm', or 'serr' then press tab for System.out.println(...);
        System.out.println();
        return "Practice your backhand volley. 🎾🏸";
    }

}
