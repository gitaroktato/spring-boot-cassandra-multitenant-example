package com.example.demo.application;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/api")
    public ResponseEntity<String> hello(@RequestParam String username) {
        User user = userRepo.findByUsername(username);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }
}