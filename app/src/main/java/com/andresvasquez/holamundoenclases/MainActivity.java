package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String user;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view) {
        this.user = "prueba";
        this.password = "123";
        boolean loginResult = login(user, password);

        Log.d("Log debug", "Result: " + loginResult);
        Log.w("Log warning", "Result: " + loginResult);
        Log.e("Log error", "Result: " + loginResult);
        Log.v("Log verbose", "Result: " + loginResult);
        Log.i("Log info", "Result: " + loginResult);
    }

    private boolean login(String user, String password) {
        return user.equals("admin") && password.equals("123");
    }
}
