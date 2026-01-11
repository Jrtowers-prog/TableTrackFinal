package com.example.tabletrackfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // In your XML, 'customerLoginButton' has text "LOGIN"
        Button btnLogin = findViewById(R.id.customerLoginButton);
        // In your XML, 'staffLoginButton' has text "SIGN UP"
        Button btnSignUp = findViewById(R.id.staffLoginButton);

        btnLogin.setOnClickListener(v -> startActivity(new Intent(this, LogIn.class)));
        btnSignUp.setOnClickListener(v -> startActivity(new Intent(this, signUp.class)));
    }
}