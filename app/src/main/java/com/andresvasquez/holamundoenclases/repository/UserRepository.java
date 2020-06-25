package com.andresvasquez.holamundoenclases.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.andresvasquez.holamundoenclases.model.User;
import com.google.gson.Gson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserRepository {

    //Paso 0: Crear la instancia de Shared preferences
    private SharedPreferences preferences;
    private Context context;
    private List<User> users = new ArrayList<>();

    public UserRepository(Context context) {
        //Paso 1: Cuando la instancia de UserRepository se crea, llenamos nuestra variable de preferencias
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
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

    public void setUserLogged(User userLogged) {
        //Obj --> String (serializar)
        String userLoggedString = new Gson().toJson(userLogged);

        //Editor y guardamos el string
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", userLoggedString);

        //Guardamos fecha y hora en miliseconds.
        editor.putLong("timestamp", Calendar.getInstance().getTime().getTime());
        editor.apply();
    }

    public User getUserLogged() {
        if (preferences.contains("user")) {
            String userLoggedString = preferences.getString("user", null);
            if (userLoggedString != null) {

                //Mostrar ultimo Login
                if (preferences.contains("timestamp")) {
                    long timestamp = preferences.getLong("timestamp", 0);
                    Date date = new Date(timestamp);
                    Log.e("Ultimo acceso", date.toLocaleString());
                }


                //String --> Obj (deserializar)
                User userLogged = new Gson().fromJson(userLoggedString, User.class);
                return userLogged;
            }
        }

        return null;
    }

    public void deleteUserLogged() {
        //Editor y eliminamos el valor
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user");
        editor.apply();
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
