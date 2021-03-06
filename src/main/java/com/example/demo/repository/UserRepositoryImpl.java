package com.example.demo.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.demo.application.TenantId;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

public class UserRepositoryImpl
        implements KeyspaceAwareUserRepository {

    @Autowired
    private TenantId tenantId;
    @Autowired
    private CassandraOperations operations;

    @Override
    public User findByUsernameAndEmail(String username, String email) {
        Select select = QueryBuilder.select().all()
                .from(tenantId.get(),"user")
                .where(QueryBuilder.eq("username", username))
                .and(QueryBuilder.eq("email", email))
                .limit(1);

        return operations.selectOne(select, User.class);
    }
}
