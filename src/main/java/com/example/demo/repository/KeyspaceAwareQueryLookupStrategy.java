package com.example.demo.repository;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.query.CassandraQueryMethod;
import org.springframework.data.cassandra.repository.query.PartTreeCassandraQuery;
import org.springframework.data.cassandra.repository.query.StringBasedCassandraQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

public class KeyspaceAwareQueryLookupStrategy implements QueryLookupStrategy {

    private static final SpelExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
    private final EvaluationContextProvider evaluationContextProvider;
    private final CassandraMappingContext mappingContext;
    private final CassandraOperations operations;

    public KeyspaceAwareQueryLookupStrategy(CassandraOperations operations,
            EvaluationContextProvider evaluationContextProvider, CassandraMappingContext mappingContext) {

        this.operations = operations;
        this.evaluationContextProvider = evaluationContextProvider;
        this.mappingContext = mappingContext;
    }

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata,
                                        ProjectionFactory factory, NamedQueries namedQueries) {

        CassandraQueryMethod queryMethod = new CassandraQueryMethod(method, metadata, factory, mappingContext);
        String namedQueryName = queryMethod.getNamedQueryName();

        if (namedQueries.hasQuery(namedQueryName)) {
            String namedQuery = namedQueries.getQuery(namedQueryName);
            return new StringBasedCassandraQuery(namedQuery, queryMethod, operations, EXPRESSION_PARSER,
                    evaluationContextProvider);
        } else if (queryMethod.hasAnnotatedQuery()) {
            return new StringBasedCassandraQuery(queryMethod, operations, EXPRESSION_PARSER,
                    evaluationContextProvider);
        } else {
            return new PartTreeCassandraQuery(queryMethod, operations);
        }
    }
}
