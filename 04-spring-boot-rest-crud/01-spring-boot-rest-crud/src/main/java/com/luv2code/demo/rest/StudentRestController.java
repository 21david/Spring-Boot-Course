package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // Define @PostConstruct to load sample student data, which will happen only once
    @PostConstruct
    public void loadData() {
        // Hardcode new students - use DB later...
        students = new ArrayList<>();
        students.add(new Student("Dwight", "Schrute"));
        students.add(new Student("Steve", "Carell"));
        students.add(new Student("Jim", "Halpert"));
    }

    // Define endpoint for /students - return list of all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        // Spring Web will automatically use Jackson to convert this to JSON.
        // The web browser will display the final JSON.
        return students;
    }

    // Define endpoint for /students/{studentId} - return the student with id studentId
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // Spring Web will automatically use Jackson to convert this to JSON
        return students.get(studentId);  // Use index for now, in reality this would access a database
    }
}
