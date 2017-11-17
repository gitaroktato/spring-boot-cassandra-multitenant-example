package com.example.demo;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraPersistentEntity;

@Configuration
public class CassandraConfig {

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints("localhost");
        return cluster;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        BasicCassandraMappingContext mappingContext =  new BasicCassandraMappingContext() {
            @Override
            public CassandraPersistentEntity<?> getPersistentEntity(Class<?> type) {
                CassandraPersistentEntity<?> entity = super.getPersistentEntity(type);
                if (entity == null) {
                    return entity;
                }
                //Create the Proxy Factory
                AspectJProxyFactory proxyFactory = new AspectJProxyFactory(entity);
                //Add Aspect class to the factory
                proxyFactory.addListener(new AdvisedSupportListener() {
                    @Override
                    public void activated(AdvisedSupport advised) {
                        // TODO
                    }

                    @Override
                    public void adviceChanged(AdvisedSupport advised) {
                        // TODO
                    }
                });
                proxyFactory.addAspect(CustomerContextApplyingInterceptor.class);
                //Get the proxy object
                return proxyFactory.getProxy();
            }
        };
        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName("sample");
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);
        return session;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}