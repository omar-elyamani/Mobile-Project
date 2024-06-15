package com.example.myapplicationdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Consult_Activity extends AppCompatActivity {
    TextView title_data, description_data, number_data;
    FloatingActionButton call_button, text_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        title_data = findViewById(R.id.title_data);
        description_data = findViewById(R.id.description_data);
        number_data = findViewById(R.id.number_data);
        call_button = findViewById(R.id.call_button);
        text_button = findViewById(R.id.sms_button);

        if (getIntent().hasExtra("title") && getIntent().hasExtra("description") && getIntent().hasExtra("number")) {
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");
            String number = getIntent().getStringExtra("number");

            title_data.setText(title);
            description_data.setText(description);
            number_data.setText(number);

            call_button.setOnClickListener(v -> {
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:" + number));
                startActivity(dial);
            });

            text_button.setOnClickListener(v -> {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:" + number));
                startActivity(smsIntent);
            });

        }
    }
}
