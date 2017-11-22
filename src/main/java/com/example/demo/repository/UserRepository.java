package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {
    User findOne(String username);
    User findByUsername(String username);
}
