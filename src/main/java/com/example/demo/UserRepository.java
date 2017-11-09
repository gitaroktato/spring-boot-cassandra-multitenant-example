package com.example.demo;

import org.springframework.data.repository.Repository;

/**
 * Created by Oresztesz_Margaritis on 11/9/2017.
 */
public interface UserRepository extends Repository<User, Long> {

    public User findByUsername(String username);
}
