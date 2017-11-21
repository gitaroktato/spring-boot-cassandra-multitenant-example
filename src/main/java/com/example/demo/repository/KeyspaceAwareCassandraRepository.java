package com.example.demo.repository;

import com.datastax.driver.core.querybuilder.Select;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Oresztesz_Margaritis on 11/19/2017.
 */
public class KeyspaceAwareCassandraRepository<T, ID extends Serializable>
        extends SimpleCassandraRepository<T, ID>  {

    private final CassandraEntityInformation<T, ID> metadata;
    private final CassandraOperations operations;

    public KeyspaceAwareCassandraRepository(
            CassandraEntityInformation<T, ID> metadata,
            CassandraOperations operations) {
        super(metadata, operations);
        this.metadata = metadata;
        this.operations = operations;
    }

    @Override
    public T findOne(ID id) {
        System.out.println("ID: == " + id);
        return super.findOne(id);
    }

    @Override
    public List<T> findAll() {
        System.out.println("FindAll");
        return operations.selectAll(metadata.getJavaType());
    }

    @Override
    protected List<T> findAll(Select query) {
        System.out.println("FindAll");
        return super.findAll(query);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        System.out.println("FindAll");
        return super.findAll(ids);
    }
}
