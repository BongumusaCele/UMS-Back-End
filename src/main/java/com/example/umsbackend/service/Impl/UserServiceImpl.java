package com.example.umsbackend.service.Impl;

import com.example.umsbackend.exception.UserDoesNotExistException;
import com.example.umsbackend.model.Users;
import com.example.umsbackend.repository.UserRepo;
import com.example.umsbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<String> createUser(Users user) {
        log.info("Creating New User {} ", user);
        try {
            if(validateRegistration(user)){
                Users myUser = userRepo.findByEmail(user.getEmail());
                if(Objects.isNull(myUser)){
                    userRepo.save(saveUser(user));
                    return new ResponseEntity<>("Your account is registered successfully. ", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);

                }
            } else{
                return  new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
        return new ResponseEntity<>("Something is very wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateUser(String userId, Users users) {
        try{
            Users users1 = userRepo.findById(userId).orElseThrow(() -> new UserDoesNotExistException());

            if(!users1.equals(null)){
                users1.setId(users.getId());
                users1.setFullname(users.getFullname());
                users1.setEmail(users.getEmail());
                users1.setPhone(users.getPhone());
                users1.setPassword(users.getPassword());
                users1.setRole(users.getRole());
                users.setIsactive(users.getIsactive());
                userRepo.save(users1);

                return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception error){
            error.printStackTrace();
        }
        return new ResponseEntity<>("Somethin is very wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public String deleteUserById(String userId) {
        userRepo.deleteById(userId);

        return "User Deleted";
    }

    @Override
    public Users getUserById(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new UserDoesNotExistException());
    }

    private boolean validateRegistration(Users user){
        if(user.getFullname() == null || user.getFullname().isEmpty() ||
                user.getId() == null || user.getId().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPhone() == null || user.getPhone().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()){
            return false;
        }
        return true;
    }

    private Users saveUser(Users user){
        Users users = new Users();

        users.setId(user.getId());
        users.setFullname(user.getFullname());
        users.setEmail(user.getEmail());
        users.setPhone(user.getPhone());
        users.setPassword(user.getPassword());
        users.setRole(user.getRole());
        users.setIsactive(user.getIsactive());

        return users;
    }
}
