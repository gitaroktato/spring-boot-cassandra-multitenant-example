package com.example.demo;

import com.datastax.driver.core.SimpleStatement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerContextApplyingInterceptor {

    @Autowired
    private CustomerContext ctx;

    @Around("execution(public * com.datastax.driver.core.Session+.*(..))")
    public Object setCustomerContext(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        SimpleStatement statement = (SimpleStatement) args[0];
        statement.setKeyspace(ctx.getCustomerContext());
        args[0] = new SimpleStatement(statement.getQueryString()
                .replaceFirst("user", ctx.getCustomerContext() + ".user"));
        return joinPoint.proceed(args);
    }

    @Around("execution(public * *.getMappingContext(..))")
    public Object testWeave5(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-- IT WORKS getMappingContext --");
        return joinPoint.proceed();
    }



}