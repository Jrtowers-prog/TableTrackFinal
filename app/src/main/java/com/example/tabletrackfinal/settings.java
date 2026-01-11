package com.example.tabletrackfinal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch swNotify = findViewById(R.id.switchNotifications);
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);

        // Load saved state
        boolean isNotifOn = prefs.getBoolean("notifications", true);
        swNotify.setChecked(isNotifOn);

        // Save on change
        swNotify.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("notifications", isChecked);
            editor.apply();
        });
    }
}