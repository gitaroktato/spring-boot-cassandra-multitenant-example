package com.example.demo.application;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TenantId tenantId;

    @RequestMapping(value = "/userByName")
    public ResponseEntity<String> getUserByUsername(
            @RequestHeader("Tenant-ID") String tenantId,
            @RequestParam String username) {
        // Setting the tenant ID
        this.tenantId.set(tenantId);
        // Finding user
        User user = userRepo.findOne(username);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @RequestMapping(value = "/userByNameAndEmail")
    public ResponseEntity<String> getUserByNameAndEmail(
            @RequestHeader("Tenant-ID") String tenantId,
            @RequestParam String username, @RequestParam String email) {
        // Setting the tenant ID
        this.tenantId.set(tenantId);
        // Finding user
        User user = userRepo.findByUsernameAndEmail(username, email);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }
}