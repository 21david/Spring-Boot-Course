package com.udemycourse.springboot.thymeleafdemo.controller;

import com.udemycourse.springboot.thymeleafdemo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${sandwich_ingredients}")
    private List<String> ingredients;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        model.addAttribute("student", new Student());
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        ingredients.set(ingredients.size() - 1, "Jalapeños"); // fix broken ñ
        model.addAttribute("ingredients", ingredients);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudent(@ModelAttribute("student") Student student) {

        System.out.println("Current student data: " + student);

        return "student-confirmation";
    }
}
