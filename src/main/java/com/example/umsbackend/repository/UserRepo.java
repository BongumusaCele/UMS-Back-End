package com.example.umsbackend.repository;

import com.example.umsbackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, String> {
    Users findByEmail(String email);
}
