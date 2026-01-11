package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class staffhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffhome);

        Button btnManageMenu = findViewById(R.id.btnManageMenu);
        Button btnViewAllRes = findViewById(R.id.btnViewAllRes);
        ImageView btnSettings = findViewById(R.id.settings);
        ImageView btnHome = findViewById(R.id.home);

        btnManageMenu.setOnClickListener(v -> startActivity(new Intent(this, Menu.class)));
        btnViewAllRes.setOnClickListener(v -> startActivity(new Intent(this, view_Reservations.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));

        // Logout Logic
        btnHome.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
            prefs.edit().clear().apply();
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LogIn.class));
            finish();
        });
    }
}
