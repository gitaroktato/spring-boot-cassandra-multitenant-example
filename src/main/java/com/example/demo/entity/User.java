package com.example.demo.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class User {

    @PrimaryKey
     private String username;

     public String getUsername() {
          return username;
     }

    public void setUsername(String username) {
        this.username = username;
    }
}
