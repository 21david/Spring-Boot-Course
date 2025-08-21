package com.udemycourse.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    // Actual validation logic
    @Override
    public boolean isValid(String userInput, ConstraintValidatorContext constraintValidatorContext) {
        if (userInput == null)
            return true;  // allow empty input

        // If there was input, it should start with LU or whatever the coursePrefix was set to
        return userInput.startsWith(coursePrefix);
    }
}
