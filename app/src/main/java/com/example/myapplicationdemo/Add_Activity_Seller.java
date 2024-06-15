package com.example.myapplicationdemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class Add_Activity_Seller extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText, numberEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setupUI();
        setupListeners();
    }

    private void setupUI() {
        titleEditText = findViewById(R.id.title_input);
        descriptionEditText = findViewById(R.id.description_input);
        numberEditText = findViewById(R.id.number_input);
        addButton = findViewById(R.id.add_button);
    }

    private void setupListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOffer();
            }
        });
    }

    private void addOffer() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String number = numberEditText.getText().toString().trim();

        if (!title.isEmpty() && !description.isEmpty() && !number.isEmpty()) {
            DB_Helper dbHelper = new DB_Helper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            DB_Helper_Offers offersDAO = new DB_Helper_Offers(db, this);

            offersDAO.addOffer(title, description, number);

            db.close();  // Close the database after the operation

            startActivity(new Intent(Add_Activity_Seller.this, Home_Menu_Seller.class));
            finish();
        } else {
            showToast("Please fill in all the fields");
        }
    }

    private void showToast(String message) {
        Toast.makeText(Add_Activity_Seller.this, message, Toast.LENGTH_SHORT).show();
    }
}
