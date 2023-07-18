package com.example.umsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ums_users")
public class Users {
    @Id
    private String id;
    private String fullname;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String isactive;
}