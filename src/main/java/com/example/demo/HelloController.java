package com.example.demo;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;


@RestController
public class HelloController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CassandraTemplate template;

    @Autowired
    private CustomerContext ctx;

    @RequestMapping(value = "/api")
    public ResponseEntity<String> hello(@RequestHeader(name = "Customer-Context") String customer,
                                        @RequestParam String username) {
        template.execute("USE " + customer);
        Select select = QueryBuilder.select().from("user")
                .where(QueryBuilder.eq("username", username)).limit(1);
        User result = template.selectOne(select, User.class);
        return new ResponseEntity<>(result.getUsername(), HttpStatus.OK);
    }
}