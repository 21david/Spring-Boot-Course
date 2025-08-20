package com.udemycourse.springdemo.mvc.rest;

import com.udemycourse.springdemo.mvc.model.Person;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        System.out.println("initBinder() running");

        // this editor will edit the strings that the user inputs in the form - it will remove leading and trailing
        // whitespace, and if the entire string is whitespace, it will take it as null (hence emptyAsNull constructor parameter set to true)
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, editor);
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "name-form";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("person") Person thePerson,  // @Valid tells SB to validate using the annotations in Person class, & put errors in BindingResult
            BindingResult br) {

        System.out.println("Last name: |" + thePerson.getLastName() + "|");  // if person inputted spaces, this should show null because of the editor

        if (br.hasErrors())
            return "name-form";  // Send them back
        else
            return "confirmation"; // Success
    }
}
