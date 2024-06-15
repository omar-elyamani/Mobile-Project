package com.example.myapplicationdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_Menu_Client extends AppCompatActivity {

    RecyclerView recyclerView;
    DB_Helper dbHelper;
    DB_Helper_Offers offersDAO;
    ArrayList<String> offer_id, offer_title, offer_number, offer_description;
    Custom_Adapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu_client);

        recyclerView = findViewById(R.id.recyclerview_client);

        dbHelper = new DB_Helper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        offersDAO = new DB_Helper_Offers(db, this);

        offer_id = new ArrayList<>();
        offer_title = new ArrayList<>();
        offer_number = new ArrayList<>();
        offer_description = new ArrayList<>();

        storeData();

        customAdapter = new Custom_Adapter(Home_Menu_Client.this, Home_Menu_Client.this, offer_id, offer_title, offer_number, offer_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home_Menu_Client.this));

        db.close(); // Close the database when done
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    public void storeData() {
        Cursor cursor = offersDAO.listOffers();
        if(cursor.getCount() == 0){
            showToast("No offers are added yet!");
        } else {
            while (cursor.moveToNext()) {
                offer_id.add(cursor.getString(0));
                offer_title.add(cursor.getString(1));
                offer_number.add(cursor.getString(2));
                offer_description.add(cursor.getString(3));
            }
        }
        cursor.close(); // Always close the cursor when done
    }

    private void showToast(String message) {
        Toast.makeText(Home_Menu_Client.this, message, Toast.LENGTH_SHORT).show();
    }
}
