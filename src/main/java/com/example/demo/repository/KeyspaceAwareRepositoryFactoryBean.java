package com.example.demo.repository;

import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * Created by Oresztesz_Margaritis on 11/22/2017.
 */
public class KeyspaceAwareRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends CassandraRepositoryFactoryBean<T, S, ID> {

    public KeyspaceAwareRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        return super.createRepositoryFactory();
    }
}
