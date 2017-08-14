package com.example.shahbazahmed.loginmvvmdatabinding.repositories;

/**
 * Created by shahbazahmed on 15/08/17.
 */

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}