package com.example.umsbackend.service;

import com.example.umsbackend.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();
    ResponseEntity<String> createUser(Users user);
    ResponseEntity<String> updateUser(String userId,  Users users);
    Users getUserById(String userId);
}
