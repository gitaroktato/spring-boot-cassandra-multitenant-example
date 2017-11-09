package com.example.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerContextApplyingInterceptor implements MethodInterceptor {

    @Autowired
    private CustomerContext ctx;
    @Autowired
    private CassandraTemplate template;

    @Override
    public Object invoke(MethodInvocation i) throws Throwable {
        template.execute("USE " + ctx.getCustomerContext());
        System.out.println("Customer context is: " + ctx.getCustomerContext());
        return i.proceed();
    }

}