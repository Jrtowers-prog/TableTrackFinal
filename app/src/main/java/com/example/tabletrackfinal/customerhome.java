package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class customerhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerhome);

        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnBook = findViewById(R.id.btnBook);
        Button btnMyBookings = findViewById(R.id.btnMyBookings);
        ImageView btnSettings = findViewById(R.id.settings);
        ImageView btnHome = findViewById(R.id.home);

        // Navigation
        btnMenu.setOnClickListener(v -> startActivity(new Intent(this, Menu.class)));
        btnBook.setOnClickListener(v -> startActivity(new Intent(this, MakeReservationActivity.class)));
        btnMyBookings.setOnClickListener(v -> startActivity(new Intent(this, view_Reservations.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));

        // Logout Logic on Home Button
        btnHome.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
            prefs.edit().clear().apply(); // Clear session
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LogIn.class));
            finish();
        });
    }
}
