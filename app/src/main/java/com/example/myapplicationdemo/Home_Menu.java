package com.example.myapplicationdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Home_Menu extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DB_Helper db;
    ArrayList<String> offer_id, offer_title, offer_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Menu.this, Add_Activity.class);
                startActivity(intent);
            }
        });

        db = new DB_Helper(Home_Menu.this);
        offer_id = new ArrayList<>();
        offer_title = new ArrayList<>();
        offer_description = new ArrayList<>();

        storeData();
    }

    public void storeData( ) {
        Cursor cursor = db.listOffer();
        if(cursor.getCount() == 0){
            showToast("No offers are added yet!");
        }else{
            while (cursor.moveToNext()) {
                offer_id.add(cursor.getString(0));
                offer_title.add(cursor.getString(1));
                offer_description.add(cursor.getString(2));
            }
        }

    }

    private void showToast(String message) {
        Toast.makeText(Home_Menu.this, message, Toast.LENGTH_SHORT).show();
    }
}