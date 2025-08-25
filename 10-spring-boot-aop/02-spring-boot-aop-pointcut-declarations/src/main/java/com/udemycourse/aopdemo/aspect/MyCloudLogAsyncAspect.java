package com.udemycourse.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

    // This uses the pointcut declaration declared above that excludes getters and setters
    @Before("com.udemycourse.aopdemo.aspect.UtilityExpressions.forDaoPackageNoGetterSetter()")  // <--- we are reusing the pointcut declaration, DRY code
    public void logToCloudAsync() {
        System.out.println("(1) ======>>> Logging to cloud in async fashion");
    }
    
}
