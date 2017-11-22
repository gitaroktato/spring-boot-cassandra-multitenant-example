package com.example.demo.repository;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactory;
import org.springframework.data.repository.query.QueryLookupStrategy;

/**
 * Created by Oresztesz_Margaritis on 11/22/2017.
 */
public class KeyspaceAwareRepositoryFactory extends CassandraRepositoryFactory {

    public KeyspaceAwareRepositoryFactory(CassandraOperations operations) {
        super(operations);
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
        return super.getQueryLookupStrategy(key);
    }
}
