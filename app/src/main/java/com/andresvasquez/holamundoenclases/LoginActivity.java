package com.andresvasquez.holamundoenclases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.andresvasquez.holamundoenclases.model.User;
import com.andresvasquez.holamundoenclases.repository.UserRepository;
import com.andresvasquez.holamundoenclases.utils.Constants;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    public static String LOG = LoginActivity.class.getName();

    //Views
    private RelativeLayout backgroundRelativeLayout;
    private Button buttonLogin;
    private Button registerButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e(LOG, "onCreate");
        getSupportActionBar().hide();

        //Verificamos si el usuario existe
        UserRepository userRepository = new UserRepository(LoginActivity.this);
        User userLogged = userRepository.getUserLogged();
        if (userLogged != null) {
            Intent menuIntent = new Intent(
                    LoginActivity.this, //Origen
                    MenuActivity.class); //Destino
            String userString = new Gson().toJson(userLogged);
            Log.e("user", userString);
            menuIntent.putExtra(Constants.INTENT_KEY_USER, userString);
            startActivity(menuIntent);
        } else {
            initViews();
            addEvents();
        }
    }

    /**
     * Llenar las variables con los elementos de la vista.
     */
    private void initViews() {
        backgroundRelativeLayout = findViewById(R.id.backgroundRelativeLayout);
        buttonLogin = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
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

                //Paso 1. Iniciar una instancia de UserRepository
                UserRepository userRepository = new UserRepository(LoginActivity.this);
                User userLogged = userRepository.login(username, password);
                if (userLogged == null) {
                    Toast.makeText(
                            LoginActivity.this, //Donde
                            getString(R.string.error_invalid_login), //Mensaje
                            Toast.LENGTH_SHORT) //Por cuanto tiempo
                            .show();
                    return;
                }

                if (rememberMeCheckBox.isChecked()) {
                    //Guardamos el objeto usuario en SharedPreferences
                    userRepository.setUserLogged(userLogged);
                }

                //NO LLEGUE ACA
                //1. Creamos el canal
                Intent menuIntent = new Intent(
                        LoginActivity.this, //Origen
                        MenuActivity.class); //Destino

                //2. Convertimos el OBJ User --> String (serializando)
                String userString = new Gson().toJson(userLogged);
                Log.e("user", userString);

                //3. Adicionamos el OBJ serializado como mensaje con la clave: "objUser"
                menuIntent.putExtra(Constants.INTENT_KEY_USER, userString);

                //4. Enviamos el mensaje
                startActivity(menuIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("PasswordChanged", charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

    public void call(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hola me interesa saber más sobre la App");
        intent.putExtra("ian", "estas atento");
        intent.setType("text/plain");
        startActivity(intent);

        /*Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "60507900"));
        startActivity(intent);*/
    }

    public void showMap(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=-16.499506,-68.123777"));
        startActivity(intent);

        /*String url = "https://attendance-68941.firebaseapp.com/home";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);*/
    }
}
