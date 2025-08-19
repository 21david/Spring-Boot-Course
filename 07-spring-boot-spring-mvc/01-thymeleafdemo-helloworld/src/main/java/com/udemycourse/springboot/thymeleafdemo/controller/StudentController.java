package com.udemycourse.springboot.thymeleafdemo.controller;

import com.udemycourse.springboot.thymeleafdemo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        model.addAttribute("student", new Student());

        return "studentform";
    }

    @PostMapping("/")
    public String processStudent(@ModelAttribute("student") Student student) {



        return "studentconfirmation";
    }
}
