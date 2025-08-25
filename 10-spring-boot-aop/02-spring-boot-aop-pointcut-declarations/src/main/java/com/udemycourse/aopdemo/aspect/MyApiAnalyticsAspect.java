package com.udemycourse.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    // This uses the pointcut declaration declared above that excludes getters and setters
    @Before("com.udemycourse.aopdemo.aspect.UtilityExpressions.forDaoPackageNoGetterSetter()")  // <--- we are reusing the pointcut declaration, DRY code
    public void performApiAnalytics() {
        System.out.println("(3) ======>>> Performing API analytics");
    }

}
