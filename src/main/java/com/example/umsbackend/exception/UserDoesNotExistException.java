package com.example.umsbackend.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(){
        super("The email does not exists");
    }
}
