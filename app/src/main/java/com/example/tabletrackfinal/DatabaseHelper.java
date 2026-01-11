package com.example.tabletrackfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Restaurant.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Menu(name TEXT primary key, description TEXT, price REAL, image TEXT)");
        db.execSQL("create Table Reservations(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, date TEXT, time TEXT, size INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Menu");
        db.execSQL("drop Table if exists Reservations");
        onCreate(db);
    }

    // Insert Menu Item
    public boolean addMenuItem(String name, String description, double price, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("price", price);
        contentValues.put("image", imageUri);
        long result = db.insert("Menu", null, contentValues);
        return result != -1;
    }

    // Get All Menu Items
    public Cursor getAllMenuItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Menu", null);
    }

    // Add Reservation
    public boolean addReservation(String username, String date, String time, int size) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("size", size);
        long result = db.insert("Reservations", null, contentValues);
        return result != -1;
    }

    // Get All Reservations (Staff)
    public Cursor getAllReservations() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Reservations", null);
    }

    // Get Specific Customer Reservations
    public Cursor getCustomerReservations(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Reservations where username = ?", new String[]{username});
    }

    // Cancel Reservation
    public boolean cancelReservation(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Reservations", "id=?", new String[]{id}) > 0;
    }
}
