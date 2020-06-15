package com.andresvasquez.holamundoenclases.repository;

import com.andresvasquez.holamundoenclases.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    //Paso 1: Crear la variable instance: static, UserRepository
    private static UserRepository instance; //Mismo tipo que la clase
    private List<User> users = new ArrayList<>();

    //Paso 2: Crear la función getInstance : static, public y nos devuelve instance
    //Todas las clases van ha llamar la instancia getInstance()
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    //Paso 3: Convertir el constructor en privado
    //Constructor es privado
    private UserRepository() {
        defaultValues();
    }

    public User login(String username, String password) {
        //Select * FROM users WHERE username = {username} AND password = {password};
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void register(User user) {
        users.add(user);
    }

    private void defaultValues() {
        User adminUser = new User("Administrador/Root", "admin", "123", "Bolivia");
        adminUser.setPhoneNumber(60407900);
        users.add(adminUser);

        User andreUser = new User("Andre Machicao", "andre", "51176", "Bolivia");
        andreUser.setPhoneNumber(68117499);
        users.add(andreUser);
    }
}
