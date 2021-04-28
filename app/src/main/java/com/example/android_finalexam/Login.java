package com.example.android_finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    // creating objects for the designing elements
    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // assigning value to the objects by finding the view by id
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // click listener event for the login button
        btnLogin.setOnClickListener(this);
    }

    // implementing the abstract method
    @Override
    public void onClick(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        // validating the data, if data is not valid then showing the toast else redirecting to the required activity
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getBaseContext(),"Please enter value for required fields", Toast.LENGTH_LONG).show();
        } else if(!(username.equalsIgnoreCase("user1") && password.equals("password1"))){
            Toast.makeText(getBaseContext(),"Username or Paaword is invalid", Toast.LENGTH_LONG).show();
        } else{
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}