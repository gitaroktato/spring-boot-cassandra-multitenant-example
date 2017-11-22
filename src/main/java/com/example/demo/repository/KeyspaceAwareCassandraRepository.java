package com.example.demo.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.demo.application.CustomerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.util.List;

public class KeyspaceAwareCassandraRepository<T, ID extends Serializable>
        extends SimpleCassandraRepository<T, ID>  {

    private final CassandraEntityInformation<T, ID> metadata;
    private final CassandraOperations operations;

    @Autowired
    private CustomerContext ctx;

    public KeyspaceAwareCassandraRepository(
            CassandraEntityInformation<T, ID> metadata,
            CassandraOperations operations) {
        super(metadata, operations);
        this.metadata = metadata;
        this.operations = operations;
    }

    private void injectDependencies() {
        SpringBeanAutowiringSupport
                .processInjectionBasedOnServletContext(this,
                getServletContext());
    }

    private ServletContext getServletContext() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getServletContext();
    }

    @Override
    public T findOne(ID id) {
        injectDependencies();
        CqlIdentifier primaryKey = operations.getConverter()
                .getMappingContext()
                .getPersistentEntity(metadata.getJavaType())
                .getIdProperty().getColumnName();

        Select select = QueryBuilder.select().all()
                .from(ctx.getCustomerContext(),
                        metadata.getTableName().toCql())
                .where(QueryBuilder.eq(primaryKey.toString(), id))
                .limit(1);

        return operations.selectOne(select, metadata.getJavaType());
    }

    @Override
    public List<T> findAll() {
        return operations.selectAll(metadata.getJavaType());
    }

    @Override
    protected List<T> findAll(Select query) {
        return super.findAll(query);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return super.findAll(ids);
    }
}
