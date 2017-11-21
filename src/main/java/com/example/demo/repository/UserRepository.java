package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Oresztesz_Margaritis on 11/9/2017.
 */
public interface UserRepository extends CrudRepository<User, String> {
    @Override
    User findOne(String username);
}
