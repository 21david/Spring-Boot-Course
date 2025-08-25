package com.udemycourse.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.udemycourse.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}  // this can have any name, basically just used as a variable name or label

    // This uses the pointcut declaration declared above
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ======>>> Executing @Before advice on addAccount()");
    }

}
