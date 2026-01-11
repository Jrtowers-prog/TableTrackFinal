package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        listView = findViewById(R.id.resListView);
        Button btnMake = findViewById(R.id.btnMakeRes);
        ImageView btnHome = findViewById(R.id.home);
        ImageView btnSettings = findViewById(R.id.settings);

        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String role = prefs.getString("role", "Guest");
        String username = prefs.getString("username", "");

        // Only guests should see "Book Table" here
        if("Staff".equalsIgnoreCase(role)){
            btnMake.setVisibility(View.GONE);
        } else {
            btnMake.setVisibility(View.VISIBLE);
        }

        btnMake.setOnClickListener(v -> startActivity(new Intent(this, MakeReservationActivity.class)));
        btnHome.setOnClickListener(v -> finish());
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));

        loadBookings(role, username);
    }

    private void loadBookings(String role, String username) {
        bookingList = new ArrayList<>();
        Cursor cursor;

        if ("Staff".equalsIgnoreCase(role)) {
            cursor = db.getAllReservations();
        } else {
            cursor = db.getCustomerReservations(username);
        }

        if (cursor.getCount() == 0) {
            bookingList.add("No reservations found.");
        } else {
            while (cursor.moveToNext()) {
                // Formatting the display string
                String data = "Ref: " + cursor.getString(0) + "\n" +
                        "Date: " + cursor.getString(2) + " Time: " + cursor.getString(3) + "\n" +
                        "Guest: " + cursor.getString(1);
                bookingList.add(data);
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingList);
        listView.setAdapter(adapter);

        // Click to cancel
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = bookingList.get(position);
            if(item.startsWith("Ref:")) {
                String resId = item.split("\n")[0].replace("Ref: ", "");
                db.cancelReservation(resId);
                Toast.makeText(this, "Reservation Cancelled", Toast.LENGTH_SHORT).show();
                loadBookings(role, username); // Refresh
            }
        });
    }
}
