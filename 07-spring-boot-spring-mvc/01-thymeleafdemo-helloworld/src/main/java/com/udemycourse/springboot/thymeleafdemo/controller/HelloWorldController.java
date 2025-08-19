package com.udemycourse.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/showForm")
    public String showForm() {


        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm() {


        return "helloworld";
    }

    @RequestMapping("/processFormV2")
    public String shout(HttpServletRequest req, Model model) {
        String name = req.getParameter("studentName");

        String shouted = "Yo! " + name.toUpperCase();

        model.addAttribute("message", shouted);

        return "helloworld";
    }
}
