package com.example.tabletrackfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Variables to your custom XML IDs
        Button btnLogin = findViewById(R.id.customerLoginButton); // In your XML, this says "LOGIN"
        Button btnSignUp = findViewById(R.id.staffLoginButton);   // In your XML, this says "SIGN UP"
        TextView tvForgot = findViewById(R.id.forgotButton);
        ImageView btnSettings = findViewById(R.id.settings);

        // 2. Login Button Logic
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LogIn.class);
            startActivity(intent);
        });

        // 3. Sign Up Button Logic
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, signUp.class);
            startActivity(intent);
        });

        // 4. Settings Button Logic
        if (btnSettings != null) {
            btnSettings.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, settings.class);
                startActivity(intent);
            });
        }

        // 5. Forgot Password Logic
        if (tvForgot != null) {
            tvForgot.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Please contact admin to reset password.", Toast.LENGTH_SHORT).show();
            });
        }
    }
}