package com.example.demo.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.demo.application.CustomerContext;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

public class UserRepositoryImpl
        implements KeyspaceAwareUserRepository {

    @Autowired
    private CustomerContext ctx;
    @Autowired
    private CassandraOperations operations;

    @Override
    public User findByUsername(String username) {
        Select select = QueryBuilder.select().all()
                .from(ctx.getCustomerContext(),"user")
                .where(QueryBuilder.eq("username", username))
                .limit(1);

        return operations.selectOne(select, User.class);
    }
}
