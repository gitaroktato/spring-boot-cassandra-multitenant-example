package com.example.demo;

import com.example.demo.repository.KeyspaceAwareCassandraRepository;
import com.example.demo.repository.KeyspaceAwareRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCassandraRepositories(
		repositoryFactoryBeanClass = KeyspaceAwareRepositoryFactoryBean.class,
		repositoryBaseClass = KeyspaceAwareCassandraRepository.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
