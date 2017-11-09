package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

    @Autowired
    private CustomerContext ctx;

    @RequestMapping(value = "/api")
    public ResponseEntity<String> hello(
            @RequestHeader(value="Customer-Context") String customerContext) {
        ctx.setCustomerContext(customerContext);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}