package com.example.myapplicationdemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private DB_Helper dbHelper;
    private DB_Helper_Sellers sellersDAO;
    EditText phone, password;
    Button loginButton, guestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        guestButton = findViewById(R.id.guestButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = phone.getText().toString();
                String pass = password.getText().toString();
                String role = null;

                if (user.equals("admin") && pass.equals("1234")) {
                    Toast.makeText(Login.this, "Login Successful as Administrator!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Home_Menu_Admin.class));
                    finish();
                } else if (!user.equals("admin") && !pass.equals("1234")) {
                    dbHelper = new DB_Helper(Login.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    sellersDAO = new DB_Helper_Sellers(db, Login.this);
                    Intent result = sellersDAO.findSellerByNumberAndPassword(user, pass);

                    Toast.makeText(Login.this, "Login Successful as Seller!", Toast.LENGTH_SHORT).show();
                    startActivity(result);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    phone.setText("");
                    password.setText("");
                }
            }
        });



        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Welcome!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, Home_Menu_Client.class));
                finish();
            }
        });
    }
}
