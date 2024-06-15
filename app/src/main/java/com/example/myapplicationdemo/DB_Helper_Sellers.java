package com.example.myapplicationdemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DB_Helper_Sellers {
    private SQLiteDatabase db;
    private Context context;

    public DB_Helper_Sellers(SQLiteDatabase db, Context context) {
        this.db = db;
        this.context = context;
    }

    public void addSeller(String fullname, String number, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_SELLER_FULL_NAME, fullname);
        cv.put(DB_Helper.COLUMN_SELLER_NUMBER, number);
        cv.put(DB_Helper.COLUMN_SELLER_PASSWORD, password);

        long result = db.insert(DB_Helper.TABLE_SELLERS, null, cv);
        showToast(result == -1 ? "The insert operation has failed!" : "The seller was registered successfully!");
    }

    public Cursor listSellers() {
        return db.rawQuery("SELECT * FROM " + DB_Helper.TABLE_SELLERS, null);
    }

    public void updateSeller(String row_id, String fullname, String number, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_SELLER_FULL_NAME, fullname);
        cv.put(DB_Helper.COLUMN_SELLER_NUMBER, number);
        cv.put(DB_Helper.COLUMN_SELLER_PASSWORD, password);

        long result = db.update(DB_Helper.TABLE_SELLERS, cv, DB_Helper.COLUMN_SELLER_ID + "=?", new String[]{row_id});
        showToast(result == -1 ? "The update operation has failed!" : "The seller information was updated successfully!");
    }

    public void deleteSeller(String row_id) {
        long result = db.delete(DB_Helper.TABLE_SELLERS, DB_Helper.COLUMN_SELLER_ID + "=?", new String[]{row_id});
        showToast(result == -1 ? "The delete operation has failed!" : "The seller was deleted successfully!");
    }

    public void deleteAllSellers() {
        db.execSQL("DELETE FROM " + DB_Helper.TABLE_SELLERS);
    }

    public Intent findSellerByNumberAndPassword(String number, String password) {
        // Define the columns to be queried
        String[] projection = {
                DB_Helper.COLUMN_SELLER_ID,
                DB_Helper.COLUMN_SELLER_FULL_NAME,
                DB_Helper.COLUMN_SELLER_NUMBER,
                DB_Helper.COLUMN_SELLER_PASSWORD
        };

        // Specify the WHERE clause
        String selection = DB_Helper.COLUMN_SELLER_NUMBER + " = ? AND " + DB_Helper.COLUMN_SELLER_PASSWORD + " = ?";

        // Specify the values for the WHERE clause
        String[] selectionArgs = { number, password };

        // Query the database
        Cursor cursor = db.query(
                DB_Helper.TABLE_SELLERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Intent intent = null;
        // Check if the cursor contains any rows
        if (cursor.moveToFirst()) {
            // Extract seller data from cursor
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DB_Helper.COLUMN_SELLER_ID));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow(DB_Helper.COLUMN_SELLER_FULL_NAME));

            // Create Intent to launch Home_Menu_Seller activity
            intent = new Intent(context, Home_Menu_Seller.class);
            intent.putExtra("seller_number", number);
        }

        // Close the cursor
        cursor.close();
        return intent;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
