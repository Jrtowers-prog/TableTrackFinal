package com.example.tabletrackfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class staffhome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffhome);

        // These IDs will now be found in the XML
        Button btnManageMenu = findViewById(R.id.btnManageMenu);
        Button btnViewAllRes = findViewById(R.id.btnViewAllRes);
        ImageView btnSettings = findViewById(R.id.settings);

        btnManageMenu.setOnClickListener(v -> startActivity(new Intent(this, Menu.class)));
        btnViewAllRes.setOnClickListener(v -> startActivity(new Intent(this, view_Reservations.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));
    }
}
