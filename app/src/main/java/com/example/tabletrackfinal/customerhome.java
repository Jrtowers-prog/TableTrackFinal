package com.example.tabletrackfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class customerhome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerhome);

        // These IDs will now be found in the XML
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnBook = findViewById(R.id.btnBook);
        Button btnMyBookings = findViewById(R.id.btnMyBookings);
        ImageView btnSettings = findViewById(R.id.settings);

        btnMenu.setOnClickListener(v -> startActivity(new Intent(this, Menu.class)));
        btnBook.setOnClickListener(v -> startActivity(new Intent(this, MakeReservationActivity.class)));
        btnMyBookings.setOnClickListener(v -> startActivity(new Intent(this, view_Reservations.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));
    }
}
