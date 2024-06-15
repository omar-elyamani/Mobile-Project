package com.example.myapplicationdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Activity extends AppCompatActivity {

    EditText title_input, description_input;
    Button update_button, delete_button;
    String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // First we call this
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // And only then we call this
                DB_Helper dbHelper = new DB_Helper(Update_Activity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                DB_Helper_Offers offersDAO = new DB_Helper_Offers(db, Update_Activity.this);

                title = title_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                offersDAO.updateOffer(id, title, description);
                startActivity(new Intent(Update_Activity.this, Home_Menu_Seller.class));
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("description")) {
            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            // Setting Intent Data
            title_input.setText(title);
            description_input.setText(description);
            Log.d("Update_Activity", "Received data: ID = " + id + ", Title = " + title + ", Description = " + description);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DB_Helper dbHelper = new DB_Helper(Update_Activity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                DB_Helper_Offers offersDAO = new DB_Helper_Offers(db, Update_Activity.this);

                offersDAO.deleteOffer(id);
                startActivity(new Intent(Update_Activity.this, Home_Menu_Seller.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });
        builder.create().show();
    }
}
