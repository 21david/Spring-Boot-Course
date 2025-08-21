package com.udemycourse.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/hello")
    public String sayHello(Model model) {

        model.addAttribute("date", java.time.LocalDateTime.now());  // basically like setting 'date' as a constant variable set to the current time

        return "helloworld";  // this passes the model data to helloworld.html thymeleaf template
        //Spring Boot will look in src/main/resources/templates/ for 'helloworld.html'
    }
}
