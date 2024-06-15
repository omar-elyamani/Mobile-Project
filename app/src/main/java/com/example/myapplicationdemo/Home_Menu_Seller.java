package com.example.myapplicationdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home_Menu_Seller extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DB_Helper dbHelper;
    DB_Helper_Offers offersDAO;
    ArrayList<String> offer_id, offer_title, offer_number, offer_description;
    Custom_Adapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu_seller);

        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Menu_Seller.this, Add_Activity_Seller.class);
                startActivity(intent);
            }
        });

        dbHelper = new DB_Helper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        offersDAO = new DB_Helper_Offers(db, this);

        offer_id = new ArrayList<>();
        offer_title = new ArrayList<>();
        offer_number = new ArrayList<>();
        offer_description = new ArrayList<>();

        storeData();

        customAdapter = new Custom_Adapter(Home_Menu_Seller.this, Home_Menu_Seller.this, offer_id, offer_title, offer_number, offer_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home_Menu_Seller.this));

        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void storeData() {
        Cursor cursor = offersDAO.listOffers();
        if (cursor.getCount() == 0) {
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
        Toast.makeText(Home_Menu_Seller.this, message, Toast.LENGTH_SHORT).show();
    }
}
