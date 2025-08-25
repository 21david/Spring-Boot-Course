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

    // Pointcut declaration that matches only getters
    @Pointcut("execution(* com.udemycourse.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // Pointcut declaration that matches only setters
    @Pointcut("execution(* com.udemycourse.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // This pointcut hits everything in this package, EXCEPT either getters or setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}



    // This uses the pointcut declaration declared above that excludes getters and setters
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ======>>> Executing @Before advice on addAccount()");
    }

    // This uses the pointcut declaration declared above that excludes getters and setters
    @Before("forDaoPackageNoGetterSetter()")  // <--- we are reusing the pointcut declaration, DRY code
    public void performApiAnalytics() {
        System.out.println("======>>> Performing API analytics");
    }

}
