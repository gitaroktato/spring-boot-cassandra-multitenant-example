package com.example.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class CustomerContextApplyingInterceptor {

    @Autowired
    private CustomerContext ctx;
    @Autowired
    private CassandraTemplate template;

    @Before("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void setCustomerContext() throws Throwable {
        template.execute("USE " + ctx.getCustomerContext());
        System.out.println("Customer context is: " + ctx.getCustomerContext());
    }

}