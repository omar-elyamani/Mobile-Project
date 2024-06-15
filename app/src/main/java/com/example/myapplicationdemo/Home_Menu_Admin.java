package com.example.myapplicationdemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home_Menu_Admin extends AppCompatActivity {

    private EditText fullnameEditText, numberEditText, passwordEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu_admin);
        setupUI();
        setupListeners();
    }

    private void setupUI() {
        fullnameEditText = findViewById(R.id.fullname_input);
        passwordEditText = findViewById(R.id.password_input);
        numberEditText = findViewById(R.id.number_input);
        addButton = findViewById(R.id.add_button);
    }

    private void setupListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSeller();
            }
        });
    }

    private void addSeller() {
        String password = passwordEditText.getText().toString().trim();
        String fullname = fullnameEditText.getText().toString().trim();
        String number = numberEditText.getText().toString().trim();

        if (password.isEmpty() || fullname.isEmpty() || number.isEmpty()) {
            showToast("Please fill in all the fields");
            return;
        }

        DB_Helper dbHelper = new DB_Helper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DB_Helper_Sellers sellersDAO = new DB_Helper_Sellers(db, this);
        sellersDAO.addSeller(fullname, number, password);
        showToast("Seller added successfully");
        startActivity(new Intent(Home_Menu_Admin.this, Home_Menu_Admin.class));
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
