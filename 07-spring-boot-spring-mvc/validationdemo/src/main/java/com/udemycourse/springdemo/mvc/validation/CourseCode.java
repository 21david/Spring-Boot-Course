package com.udemycourse.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)  // constraint logic is in that class
@Target({ElementType.METHOD, ElementType.FIELD})  // can be used on methods and fields
@Retention(RetentionPolicy.RUNTIME)  // How long this will be "retained" in the bytecode
public @interface CourseCode {

    public String value() default "LU";

    public String message() default "must start with LU";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
