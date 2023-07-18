package com.example.umsbackend.controller;

import com.example.umsbackend.model.Users;
import com.example.umsbackend.service.Impl.UserServiceImpl;
import com.example.umsbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestBody Users users){
        try {
            return userService.createUser(users);
        } catch (Exception error){
            error.printStackTrace();
        }
        return new ResponseEntity<>("Something is very wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody Users users){
        try{
            return userService.updateUser(userId, users);
        } catch (Exception error){
            error.printStackTrace();
        }
        return new ResponseEntity<>("Something is very wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get-users/{userId}")
    public Users getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return userService.deleteUserById(userId);
    }
}
