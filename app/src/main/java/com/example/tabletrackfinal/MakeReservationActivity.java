package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MakeReservationActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etDate, etTime, etSize;
    Button btnConfirm;
    ImageView btnHome, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        db = new DatabaseHelper(this);

        // Link XML elements
        etDate = findViewById(R.id.etResDate);
        etTime = findViewById(R.id.etResTime);
        etSize = findViewById(R.id.etResSize);
        btnConfirm = findViewById(R.id.btnConfirmRes);
        btnHome = findViewById(R.id.home);
        btnSettings = findViewById(R.id.settings);

        // Header Navigation Logic
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(MakeReservationActivity.this, customerhome.class);
            startActivity(intent);
            finish();
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MakeReservationActivity.this, settings.class);
            startActivity(intent);
        });

        // Booking Logic
        btnConfirm.setOnClickListener(v -> {
            // 1. Get User Input
            String date = etDate.getText().toString();
            String time = etTime.getText().toString();
            String sizeStr = etSize.getText().toString();

            // 2. Validation
            if(date.isEmpty() || time.isEmpty() || sizeStr.isEmpty()){
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // 3. Get Username from Session
            SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
            String username = prefs.getString("username", "Guest");

            // 4. Save to Database
            int size = Integer.parseInt(sizeStr);
            boolean success = db.addReservation(username, date, time, size);

            if(success){
                // Check Notification Settings
                boolean notify = prefs.getBoolean("notifications", true);
                if(notify) {
                    Toast.makeText(this, "Booking Confirmed! (Notification Sent)", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Booking Confirmed Successfully", Toast.LENGTH_SHORT).show();
                }
                finish(); // Close screen and go back
            } else {
                Toast.makeText(this, "Error: Could not save booking.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}