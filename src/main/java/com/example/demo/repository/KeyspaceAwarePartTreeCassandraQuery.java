package com.example.demo.repository;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraParameterAccessor;
import org.springframework.data.cassandra.repository.query.CassandraQueryMethod;
import org.springframework.data.cassandra.repository.query.PartTreeCassandraQuery;

/**
 * Created by Oresztesz_Margaritis on 11/22/2017.
 */
public class KeyspaceAwarePartTreeCassandraQuery extends PartTreeCassandraQuery {
    /**
     * Creates a new {@link PartTreeCassandraQuery} from the given {@link QueryMethod} and {@link CassandraTemplate}.
     *
     * @param queryMethod must not be {@literal null}.
     * @param operations  must not be {@literal null}.
     */
    public KeyspaceAwarePartTreeCassandraQuery(CassandraQueryMethod queryMethod, CassandraOperations operations) {
        super(queryMethod, operations);
    }

    @Override
    protected String createQuery(CassandraParameterAccessor parameterAccessor) {
        return super.createQuery(parameterAccessor);
    }
}
