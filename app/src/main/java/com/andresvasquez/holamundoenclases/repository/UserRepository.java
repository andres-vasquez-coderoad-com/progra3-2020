package com.andresvasquez.holamundoenclases.repository;

import com.andresvasquez.holamundoenclases.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public User login(String username, String password) {
        if (username.equals("admin") && password.equals("123")) {
            User user = new User("Administrador/Root", username, password, "Bolivia");
            user.setPhoneNumber(60407900);
            return user;
        } else if (username.equals("andre") && password.equals("51176")) {
            User user = new User("Andre Machicao", username, password, "Bolivia");
            user.setPhoneNumber(68117499);
            return user;
        } else {
            return null;
        }
    }
}
