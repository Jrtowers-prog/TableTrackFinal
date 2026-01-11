package com.example.tabletrackfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Link Inputs
        etUser = findViewById(R.id.usernameInput);
        etPass = findViewById(R.id.passwordInput);

        // Link Buttons
        Button btnCust = findViewById(R.id.customerLoginButton);
        Button btnStaff = findViewById(R.id.staffLoginButton);
        ImageView btnHome = findViewById(R.id.home);
        ImageView btnSettings = findViewById(R.id.settings);

        // Link Text Actions
        TextView tvForgot = findViewById(R.id.tvForgotPassword);
        TextView tvSignUp = findViewById(R.id.tvSignUp);

        // Click Listeners
        btnCust.setOnClickListener(v -> login());
        btnStaff.setOnClickListener(v -> login());

        // Settings Button
        if (btnSettings != null) {
            btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));
        }

        // Home Button (Go back to Welcome Screen)
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(LogIn.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        }

        // Sign Up Logic
        if (tvSignUp != null) {
            tvSignUp.setOnClickListener(v -> {
                Intent intent = new Intent(LogIn.this, signUp.class);
                startActivity(intent);
            });
        }

        // Forgot Password Logic
        if (tvForgot != null) {
            tvForgot.setOnClickListener(v -> {
                Toast.makeText(LogIn.this, "Contact admin for password reset.", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void login() {
        String username = etUser.getText().toString();
        String password = etPass.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
            return;
        }

        RetrofitClient.getService().getUser(username).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body().getUser();
                    if (user != null && user.getPassword().equals(password)) {

                        // Save Session
                        getSharedPreferences("UserSession", MODE_PRIVATE).edit()
                                .putString("username", user.getUsername())
                                .putString("role", user.getUserType())
                                .apply();

                        // Redirect
                        if ("Staff".equalsIgnoreCase(user.getUserType())) {
                            startActivity(new Intent(LogIn.this, staffhome.class));
                        } else {
                            startActivity(new Intent(LogIn.this, customerhome.class));
                        }
                        finish();
                    } else {
                        Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogIn.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LogIn.this, "Network Error. Check Server.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}