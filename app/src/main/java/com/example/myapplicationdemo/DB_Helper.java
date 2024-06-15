package com.example.myapplicationdemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB_Helper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "OfferShop.db";
    private static final int DB_VERSION = 3;

    // Sellers Table
    public static final String TABLE_SELLERS = "sellers";
    public static final String COLUMN_SELLER_ID = "_id";
    public static final String COLUMN_SELLER_FULL_NAME = "seller_full_name";
    public static final String COLUMN_SELLER_NUMBER = "seller_number";
    public static final String COLUMN_SELLER_PASSWORD = "seller_password";

    private static final String CREATE_TABLE_SELLERS = "CREATE TABLE " + TABLE_SELLERS + " (" +
            COLUMN_SELLER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SELLER_FULL_NAME + " TEXT, " +
            COLUMN_SELLER_NUMBER + " TEXT, " +
            COLUMN_SELLER_PASSWORD + " TEXT);";

    // Offers Table
    public static final String TABLE_OFFERS = "offers";
    public static final String COLUMN_OFFER_ID = "_id";
    public static final String COLUMN_OFFER_TITLE = "offer_title";
    public static final String COLUMN_OFFER_DESCRIPTION = "offer_description";
    public static final String COLUMN_OFFER_NUMBER = "offer_number";

    private static final String CREATE_TABLE_OFFERS = "CREATE TABLE " + TABLE_OFFERS + " (" +
            COLUMN_OFFER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_OFFER_TITLE + " TEXT, " +
            COLUMN_OFFER_NUMBER + " TEXT, " +
            COLUMN_OFFER_DESCRIPTION + " TEXT);";

    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SELLERS);
        db.execSQL(CREATE_TABLE_OFFERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELLERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFERS);
        onCreate(db);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    //DB Helper for Offers table
    public void addOffer(String title, String description, String number, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_OFFER_TITLE, title);
        cv.put(DB_Helper.COLUMN_OFFER_DESCRIPTION, description);
        cv.put(DB_Helper.COLUMN_OFFER_NUMBER, number);

        long result = db.insert(TABLE_OFFERS, null, cv);
        if (result == -1) {
            showToast("The insert operation has failed!");
        } else {
            showToast("Your offer was added successfully!");
        }

    }

    public Cursor listOffers(SQLiteDatabase db) {
        return db.rawQuery("SELECT * FROM " + DB_Helper.TABLE_OFFERS, null);
    }

    public void updateOffer(String row_id, String title, String description, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_OFFER_TITLE, title);
        cv.put(DB_Helper.COLUMN_OFFER_DESCRIPTION, description);

        long result = db.update(DB_Helper.TABLE_OFFERS, cv, DB_Helper.COLUMN_OFFER_ID + "=?", new String[]{row_id});
        showToast(result == -1 ? "The update operation has failed!" : "Your offer was updated successfully!");
    }

    public void deleteOffer(String row_id, SQLiteDatabase db) {
        long result = db.delete(DB_Helper.TABLE_OFFERS, DB_Helper.COLUMN_OFFER_ID + "=?", new String[]{row_id});
        showToast(result == -1 ? "The delete operation has failed!" : "Your offer was deleted successfully!");
    }



    //DB Helper for Sellers table
    public void addSeller(String fullname, String number, String password, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_SELLER_FULL_NAME, fullname);
        cv.put(DB_Helper.COLUMN_SELLER_NUMBER, number);
        cv.put(DB_Helper.COLUMN_SELLER_PASSWORD, password);

        long result = db.insert(DB_Helper.TABLE_SELLERS, null, cv);
        showToast(result == -1 ? "The insert operation has failed!" : "The seller was registered successfully!");
    }

    public boolean validateSellerCredentials(String number, String password, SQLiteDatabase db) {
        // Define the columns to be queried (both number and password)
        String[] projection = {
                DB_Helper.COLUMN_SELLER_NUMBER,
                DB_Helper.COLUMN_SELLER_PASSWORD
        };

        // Specify the WHERE clause (checking both number and password)
        String selection = DB_Helper.COLUMN_SELLER_NUMBER + " = ? AND " + DB_Helper.COLUMN_SELLER_PASSWORD + " = ?";

        // Specify the values for the WHERE clause
        String[] selectionArgs = { number, password };

        // Query the database (check if seller exists with provided credentials)
        Cursor cursor = db.query(
                DB_Helper.TABLE_SELLERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Check if the cursor contains any rows (seller exists with matching credentials)
        boolean isValid = cursor.moveToFirst();

        // Close the cursor
        cursor.close();

        return isValid;
    }

}
