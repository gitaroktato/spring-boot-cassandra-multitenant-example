package com.example.demo.application;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class TenantId {

    private String tenantId;

    public void setTenantId(String id) {
        this.tenantId = id;
    }

    public String getTenantId() {
        return tenantId;
    }
}
