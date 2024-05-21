package com.example.myapplicationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                    String role = null;

                if (user.equals("client") && pass.equals("1234") /*&& role.equals("client")*/) {
                    Toast.makeText(Login.this, "Login Successful as Client!", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(Login.this, Home_Menu.class));
                    finish();
                } else if (user.equals("0") && pass.equals("0") /*&& role.equals("seller")*/) {
                    Toast.makeText(Login.this, "Login Successful as Seller!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Home_Menu_Seller.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }
            }
        });
    }
}