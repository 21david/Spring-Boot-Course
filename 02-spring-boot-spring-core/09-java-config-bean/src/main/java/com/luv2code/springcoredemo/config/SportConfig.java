package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    // @Bean  // This bean will have an id of swimCoach (default is the method name)
    @Bean("aquatic")  // Now this one will have an id of aquatic
    public Coach swimCoach() {
        return new SwimCoach();
    }

}
