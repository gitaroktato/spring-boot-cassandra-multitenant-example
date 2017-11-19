package com.example.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.stereotype.Component;


@Component
public class GetTableNameAdvice implements MethodInterceptor {

    @Autowired
    private CustomerContext ctx;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (invocation.getMethod().getName().equals("getTableName")) {
            System.out.println("-- CONTEXT -- " + ctx.getCustomerContext());
            CqlIdentifier result = (CqlIdentifier)invocation.proceed();
            // Adding keyspace as prefix
            // FIXME: CqlIdentifier dropping this table defintion.
            // Doesn't pass REGEXP valiation
            return new CqlIdentifier(ctx.getCustomerContext()
                    + "." + result.getUnquoted(), result.isQuoted());
        } else {
            return invocation.proceed();
        }
    }
}
