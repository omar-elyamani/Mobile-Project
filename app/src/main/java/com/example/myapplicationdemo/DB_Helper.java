package com.example.myapplicationdemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DB_Helper extends SQLiteOpenHelper {

    private static final String DB_NAME = "OfferShop.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "offers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "offer_title";
    private static final String COLUMN_DESCRIPTION = "offer_description";
    private final Context context;

    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addOffer(String title, String description) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TITLE, title);
            cv.put(COLUMN_DESCRIPTION, description);
            long result = db.insert(TABLE_NAME, null, cv);

            if (result == -1) {
                showToast("The insert operation has failed!");
            } else {
                showToast("Your offer was added successfully!");
            }
        }
    }

    public Cursor listOffers() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public void updateOffer(String row_id, String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});

        if(result == -1){
            showToast("The update operation has failed!");
        } else {
            showToast("Your offer was updated successfully!");
        }
    }

    public void deleteOffer(String row_id) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id});
            if (result == -1) {
                showToast("Failed to delete.");
            } else {
                showToast("Successfully deleted.");
            }
        }
    }

    public void deleteAllOffers() {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.execSQL("DELETE FROM " + TABLE_NAME);
        }
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
