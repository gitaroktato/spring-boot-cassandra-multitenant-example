package com.example.demo.repository;

import com.example.demo.entity.User;

public interface KeyspaceAwareUserRepository {
    User findByUsername(String username);
}
