package com.andresvasquez.holamundoenclases;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andresvasquez.holamundoenclases.model.User;
import com.andresvasquez.holamundoenclases.utils.DimensionUtils;

public class RegisterActivity extends AppCompatActivity {
    public static String LOG = RegisterActivity.class.getName();

    private Context context;
    private LinearLayout parentLinearLayout;

    private TextView usernameTextview;
    private EditText usernameEditText;

    private TextView passwordTextview;
    private EditText passwordEditText;

    private TextView nameTextview;
    private EditText nameEditText;

    private TextView phoneTextview;
    private EditText phoneEditText;

    private TextView countryTextview;
    private EditText countryEditText;

    private TextView genderTextview;
    private RadioGroup genderRadioGroup;

    private LinearLayout buttonsLinearLayout;
    private Button sendButton;
    private Button cleanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(render());
        addEvents();
    }

    private View render() {
        parentLinearLayout = new LinearLayout(context);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        parentLinearLayout.setBackground(getResources().getDrawable(R.drawable.style_form_login));
        parentLinearLayout.setPadding(
                DimensionUtils.getPixelsFromDPs(this, 16),//Left
                DimensionUtils.getPixelsFromDPs(this, 16),//Top
                DimensionUtils.getPixelsFromDPs(this, 16),//Right
                0);//Bottom

        //Pasos
        //1. Creamos el obj
        //2. Adicionamos las propiedades
        //3. Agregamos al padre

        //Label
        usernameTextview = new TextView(context);
        usernameTextview.setText("Enter username");
        parentLinearLayout.addView(usernameTextview);
        //Campo
        usernameEditText = new EditText(context);
        parentLinearLayout.addView(usernameEditText);

        //Label
        passwordTextview = new TextView(context);
        passwordTextview.setText("Enter password");
        parentLinearLayout.addView(passwordTextview);
        //Campo
        passwordEditText = new EditText(context);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        parentLinearLayout.addView(passwordEditText);

        //Label
        nameTextview = new TextView(context);
        nameTextview.setText("Enter Full name");
        parentLinearLayout.addView(nameTextview);
        //Campo
        nameEditText = new EditText(context);
        parentLinearLayout.addView(nameEditText);

        //Label
        countryTextview = new TextView(context);
        countryTextview.setText("Enter country");
        parentLinearLayout.addView(countryTextview);
        //Campo
        countryEditText = new EditText(context);
        parentLinearLayout.addView(countryEditText);

        //Label
        phoneTextview = new TextView(context);
        phoneTextview.setText("Enter phone");
        parentLinearLayout.addView(phoneTextview);
        //Campo
        phoneEditText = new EditText(context);
        phoneEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        parentLinearLayout.addView(phoneEditText);

        //Label
        genderTextview = new TextView(context);
        genderTextview.setText("Gender");
        //Campo
        genderRadioGroup = new RadioGroup(context);

        //Llenar las opciones
        String[] genders = new String[]{"Male", "Female", "Not specified", "Other"};
        for (int i = 0; i < genders.length; i++) {
            RadioButton option = new RadioButton(context);
            option.setId(i);
            option.setText(genders[i]);
            genderRadioGroup.addView(option);
        }
        genderRadioGroup.check(0);
        parentLinearLayout.addView(genderRadioGroup);

        //Botones
        buttonsLinearLayout = new LinearLayout(context);
        buttonsLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,//Ancho
                ViewGroup.LayoutParams.WRAP_CONTENT);//Alto
        buttonLayoutParams.setMargins(0,
                DimensionUtils.getPixelsFromDPs(this, 20),
                0,
                0);
        buttonsLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        buttonsLinearLayout.setLayoutParams(buttonLayoutParams);

        LinearLayout.LayoutParams buttonsLayoutParams = new LinearLayout.LayoutParams(
                0,//Ancho
                ViewGroup.LayoutParams.WRAP_CONTENT,//Alto
                50);//Peso

        sendButton = new Button(context);
        sendButton.setText(getString(R.string.send));
        sendButton.setLayoutParams(buttonsLayoutParams);
        buttonsLinearLayout.addView(sendButton);

        cleanButton = new Button(context);
        cleanButton.setText(getString(R.string.reset));
        cleanButton.setLayoutParams(buttonsLayoutParams);
        buttonsLinearLayout.addView(cleanButton);

        parentLinearLayout.addView(buttonsLinearLayout);
        return parentLinearLayout;
    }

    private void addEvents() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String country = countryEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                if (username.isEmpty()) {
                    Toast.makeText(context,
                            getString(R.string.error_empty_variable, "Username"),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(context,
                            getString(R.string.error_empty_variable, "Password"),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.isEmpty()) {
                    Toast.makeText(context,
                            getString(R.string.error_empty_variable, "Name"),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (country.isEmpty()) {
                    Toast.makeText(context,
                            getString(R.string.error_empty_variable, "Country"),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.isEmpty()) {
                    Toast.makeText(context,
                            getString(R.string.error_empty_variable, "Phone"),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(phone.length() == 8 && !phone.contains(" "))) {
                    Toast.makeText(context,
                            getString(R.string.error_invalid_phone),
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                int phoneNumber = 0;
                try {
                    phoneNumber = Integer.parseInt(phone);
                } catch (Exception ex) {
                    Toast.makeText(context,
                            getString(R.string.error_empty_variable),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                User objUser = new User(name, username, password, country);
                objUser.setPhoneNumber(phoneNumber);
                finish();
            }
        });

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEditText.setText("");
                passwordEditText.setText("");
                nameEditText.setText("");
                countryEditText.setText("");
                phoneEditText.setText("");
            }
        });
    }
}
