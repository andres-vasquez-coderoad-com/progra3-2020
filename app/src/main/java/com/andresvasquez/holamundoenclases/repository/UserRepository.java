package com.andresvasquez.holamundoenclases.repository;

import com.andresvasquez.holamundoenclases.model.User;

public class UserRepository {

    public User login(String username, String password) {
        if (username.equals("admin") && password.equals("123")) {
            User user = new User("Administrador/Root", username, password, "Bolivia");
            return user;
        } else if (username.equals("andre") && password.equals("51176")) {
            User user = new User("Andre Machicao", username, password, "Bolivia");
            return user;
        } else {
            return null;
        }
    }
}
