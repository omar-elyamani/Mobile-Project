package com.example.myapplicationdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DB_Helper_Offers {
    private static final String TAG = "OffersDAO";
    private SQLiteDatabase db;
    private Context context;

    public DB_Helper_Offers(SQLiteDatabase db, Context context) {
        this.db = db;
        this.context = context;
    }

    public void addOffer(String title, String description, String number) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_OFFER_TITLE, title);
        cv.put(DB_Helper.COLUMN_OFFER_DESCRIPTION, description);
        cv.put(DB_Helper.COLUMN_OFFER_NUMBER, number);

        long result = db.insert(DB_Helper.TABLE_OFFERS, null, cv);
        if (result == -1) {
            showToast("The insert operation has failed!");
        } else {
            showToast("Your offer was added successfully!");
        }

    }

    public Cursor listOffers() {
        return db.rawQuery("SELECT * FROM " + DB_Helper.TABLE_OFFERS, null);
    }

    public void updateOffer(String row_id, String title, String description) {
        ContentValues cv = new ContentValues();
        cv.put(DB_Helper.COLUMN_OFFER_TITLE, title);
        cv.put(DB_Helper.COLUMN_OFFER_DESCRIPTION, description);

        long result = db.update(DB_Helper.TABLE_OFFERS, cv, DB_Helper.COLUMN_OFFER_ID + "=?", new String[]{row_id});
        showToast(result == -1 ? "The update operation has failed!" : "Your offer was updated successfully!");
    }

    public void deleteOffer(String row_id) {
        long result = db.delete(DB_Helper.TABLE_OFFERS, DB_Helper.COLUMN_OFFER_ID + "=?", new String[]{row_id});
        showToast(result == -1 ? "The delete operation has failed!" : "Your offer was deleted successfully!");
    }

    public void deleteAllOffers() {
        db.execSQL("DELETE FROM " + DB_Helper.TABLE_OFFERS);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
