package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        // Check  the studentId against list size
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student ID not found - " + studentId);
        }
        return students.get(studentId);  // Use index for now, in reality this would access a database
    }

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    // By putting the custom exception class as a parameter, and using the @ExceptionHandler notation,
    // Spring will make this method run whenever that exception is thrown in and not already caught by a controller
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        // Create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse(
            HttpStatus.NOT_FOUND.value(),   // int status
            exc.getMessage()                // String message
        );

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Video 109
    // Add another exception handler to catch any other exception (catch all)
    // Examples: for studentId, input 2147483648, -2147483649, asdfasdf
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
        StudentErrorResponse error = new StudentErrorResponse(
            HttpStatus.BAD_REQUEST.value(),   // int status
            ex.getMessage()                // String message
        );

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
