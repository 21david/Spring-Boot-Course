package com.luv2code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

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

        // Return ResponseEntity, Jackson converts to JSON for us
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

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
