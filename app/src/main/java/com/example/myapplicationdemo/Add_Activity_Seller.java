package com.example.myapplicationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class Add_Activity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
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
        addButton = findViewById(R.id.add_button);
        // Enable edge-to-edge display.
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        controller.setAppearanceLightStatusBars(true);
        controller.hide(WindowInsetsCompat.Type.systemBars());
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
        if (!title.isEmpty() && !description.isEmpty()) {
            DB_Helper_Offers db = new DB_Helper_Offers(this);
            db.addOffer(title, description);
            startActivity(new Intent(Add_Activity.this, Home_Menu_Seller.class));
            finish();
        } else {
            showToast("Please fill in all the fields");
        }
    }

    private void showToast(String message) {
        Toast.makeText(Add_Activity.this, message, Toast.LENGTH_SHORT).show();
    }
}
