package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Aspect
public class CustomerContextApplyingInterceptor {

    @Pointcut("execution(public * org.springframework.data.cassandra.mapping.CassandraPersistentEntity+.getTableName())")
    private void selectGetTableName(){}

    @Around("selectGetTableName()")
    @Autowired
    public Object testWeave5(ProceedingJoinPoint joinPoint) throws Throwable {
        WebApplicationContext cc = ContextLoader.getCurrentWebApplicationContext();
        CustomerContext ctx = cc.getBean(CustomerContext.class);
        CqlIdentifier result = (CqlIdentifier)joinPoint.proceed();
        // Adding keyspace as prefix
        return new CqlIdentifier(ctx.getCustomerContext()
                + "." + result.getUnquoted(), result.isQuoted());
    }
}
