package com.example.shahbazahmed.loginmvvmdatabinding.repositories;

import com.example.shahbazahmed.loginmvvmdatabinding.entities.User;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public interface UserRepository {
    void save(User user) throws UserAlreadyExistsException;

    User fetchByEmail(String email);

    void update(User user);
}
