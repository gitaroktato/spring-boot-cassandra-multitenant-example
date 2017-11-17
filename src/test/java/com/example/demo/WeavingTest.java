package com.example.demo;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.mapping.BasicCassandraPersistentEntity;
import org.springframework.data.cassandra.mapping.CassandraPersistentEntity;
import org.springframework.data.util.ClassTypeInformation;

/**
 * Created by Oresztesz_Margaritis on 11/17/2017.
 */
public class WeavingTest {

    @Test
    public void testWeave() {
        //Create the Proxy Factory
        ClassTypeInformation<User> info = ClassTypeInformation.from(User.class);
        CassandraPersistentEntity<User> entity
                = new BasicCassandraPersistentEntity<>(info);
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(entity);
        //Add Aspect class to the factory
        proxyFactory.addAspect(new CustomerContextApplyingInterceptor());
        //Get the proxy object
        CassandraPersistentEntity result = proxyFactory.getProxy();
        CqlIdentifier tableName = result.getTableName();
        // TODO assert with aspect
    }
}
