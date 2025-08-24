package com.udemycourse.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // This says "Call this before any execution of a method that has the signature 'public void addAccount()'"
    // This is an 'advice'
    // "execution" is a "pointcut expression"
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ======>>> Executing @Before advice on addAccount()");
    }

}
