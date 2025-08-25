package com.udemycourse.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // Tip: right click UtilityExpressions file in IntelliJ -> Copy Path/Reference... -> Copy Reference (at the bottom)
    // to get the full name that is needed here: com.udemycourse.aopdemo.aspect.UtilityExpressions
    @Before("com.udemycourse.aopdemo.aspect.UtilityExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint jp) {
        System.out.println("(2) ======>>> Executing @Before advice on addAccount()");

        // Print method signature
        MethodSignature signature = (MethodSignature) jp.getSignature();
        System.out.println("Method: " + signature);

    }

}
