package com.example.myapplicationdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    public Cursor listOffer() {
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
