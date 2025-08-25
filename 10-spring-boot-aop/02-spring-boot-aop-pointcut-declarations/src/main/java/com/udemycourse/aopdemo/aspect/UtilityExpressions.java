package com.udemycourse.aopdemo.aspect;


import org.aspectj.lang.annotation.Pointcut;

// Putting the pointcut declarations and making the methods public makes all of these
// accessible to the other aspect classes in this package
public class UtilityExpressions {

    @Pointcut("execution(* com.udemycourse.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}  // this can have any name, basically just used as a variable name or label

    // Pointcut declaration that matches only getters
    @Pointcut("execution(* com.udemycourse.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // Pointcut declaration that matches only setters
    @Pointcut("execution(* com.udemycourse.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // This pointcut hits everything in this package, EXCEPT either getters or setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}


}
