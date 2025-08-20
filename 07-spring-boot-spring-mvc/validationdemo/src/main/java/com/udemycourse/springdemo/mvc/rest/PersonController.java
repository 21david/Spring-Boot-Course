package com.udemycourse.springdemo.mvc.rest;

import com.udemycourse.springdemo.mvc.model.Person;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "name-form";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("person") Person thePerson,  // @Valid tells SB to validate using the annotations in Person class, & put errors in BindingResult
            BindingResult br) {

        if (br.hasErrors())
            return "name-form";  // Send them back
        else
            return "confirmation"; // Success
    }
}
