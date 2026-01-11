package com.example.tabletrackfinal;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class view_Reservations extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
    ArrayList<String> bookingList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.resListView); // Ensure this ID is in activity_view_reservations.xml
        ImageView btnHome = findViewById(R.id.home);

        btnHome.setOnClickListener(v -> finish());

        loadBookings();
    }

    private void loadBookings() {
        bookingList = new ArrayList<>();

        // 1. Get current user info
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String role = prefs.getString("role", "Guest");
        String username = prefs.getString("username", "");

        Cursor cursor;

        // 2. Decide what to fetch based on role
        if ("Staff".equalsIgnoreCase(role)) {
            cursor = db.getAllReservations(); // Staff sees everything
        } else {
            cursor = db.getCustomerReservations(username); // Customer sees own
        }

        // 3. Process data
        if (cursor.getCount() == 0) {
            bookingList.add("No reservations found.");
        } else {
            while (cursor.moveToNext()) {
                // Column 0 is ID, 1 is User, 2 is Date, 3 is Time
                String data = "Ref: " + cursor.getString(0) + "\n" +
                        "Date: " + cursor.getString(2) + " Time: " + cursor.getString(3) + "\n" +
                        "User: " + cursor.getString(1);
                bookingList.add(data);
            }
        }

        // 4. Show in list
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingList);
        listView.setAdapter(adapter);

        // Simple cancellation on click
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = bookingList.get(position);
            if(item.startsWith("Ref:")) {
                // Extract ID crudely for simplicity
                String resId = item.split("\n")[0].replace("Ref: ", "");
                db.cancelReservation(resId);
                Toast.makeText(this, "Reservation Cancelled", Toast.LENGTH_SHORT).show();
                loadBookings(); // Refresh list
            }
        });
    }
}
