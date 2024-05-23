package com.example.myapplicationdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Consult_Activity extends AppCompatActivity {
    TextView title_data, description_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        title_data = findViewById(R.id.title_data);
        description_data = findViewById(R.id.description_data);

        // Get the intent and set the data
        if (getIntent().hasExtra("title") && getIntent().hasExtra("description")) {
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");

            title_data.setText(title);
            description_data.setText(description);
        }
    }
}