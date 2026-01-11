package com.example.tabletrackfinal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // 1. Link items from XML
        Switch swNotify = findViewById(R.id.switchNotifications);
        ImageView btnHome = findViewById(R.id.home); // This was missing in your Java logic

        // 2. Setup Shared Preferences
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);

        // 3. Load saved state (Default to true)
        boolean isNotifOn = prefs.getBoolean("notifications", true);
        swNotify.setChecked(isNotifOn);

        // 4. Save state when changed
        swNotify.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("notifications", isChecked);
            editor.apply();

            if(isChecked){
                Toast.makeText(this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        // 5. Handle Home Button Click (Closes settings screen)
        btnHome.setOnClickListener(v -> finish());
    }
}