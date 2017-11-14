package com.example.demo;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    public User findByUsername(String username);
}
