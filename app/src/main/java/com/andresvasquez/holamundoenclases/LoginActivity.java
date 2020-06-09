package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.andresvasquez.holamundoenclases.model.User;
import com.andresvasquez.holamundoenclases.repository.UserRepository;

public class LoginActivity extends AppCompatActivity {

    public static String LOG = LoginActivity.class.getName();

    //Views
    private RelativeLayout backgroundRelativeLayout;
    private Button buttonLogin;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e(LOG, "onCreate");
        getSupportActionBar().hide();

        initViews();
        addEvents();
    }

    /**
     * Llenar las variables con los elementos de la vista.
     */
    private void initViews() {
        backgroundRelativeLayout = findViewById(R.id.backgroundRelativeLayout);
        buttonLogin = findViewById(R.id.loginButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    /**
     * Agregar eventos sobre los botones
     */
    private void addEvents() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty()) {
                    usernameEditText.setError(getString(R.string.error_empty));
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("The value is required");
                    return;
                }

                UserRepository repository = new UserRepository();
                User userLogged = repository.login(username, password);
                if (userLogged == null) {
                    Toast.makeText(
                            LoginActivity.this, //Donde
                            getString(R.string.error_invalid_login), //Mensaje
                            Toast.LENGTH_SHORT) //Por cuanto tiempo
                            .show();
                    return;
                }

                //NO LLEGUE ACA
                Intent menuIntent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(menuIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(LOG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(LOG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(LOG, "onDestroy");
    }


}
