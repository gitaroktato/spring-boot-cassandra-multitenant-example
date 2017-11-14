package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerContext {

    private String customerContext;

    public void setCustomerContext(String ctx) {
        this.customerContext = ctx;
    }

    public String getCustomerContext() {
        return customerContext;
    }
}
