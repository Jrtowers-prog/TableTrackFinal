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
        // Create Menu Table
        db.execSQL("create Table Menu(name TEXT primary key, description TEXT, price REAL, image TEXT)");

        // Create Reservations Table
        // ID (Auto-increment), Username (links to login), Date, Time, Size
        db.execSQL("create Table Reservations(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, date TEXT, time TEXT, size INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Menu");
        db.execSQL("drop Table if exists Reservations");
        onCreate(db);
    }

    // --- MENU METHODS ---
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

    public Cursor getAllMenuItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Menu", null);
    }

    // --- RESERVATION METHODS ---
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

    public Cursor getAllReservations() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Reservations", null);
    }

    public Cursor getCustomerReservations(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Reservations where username = ?", new String[]{username});
    }

    public boolean cancelReservation(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Reservations", "id=?", new String[]{id});
        return result > 0;
    }
}
