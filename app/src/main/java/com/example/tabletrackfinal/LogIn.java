package com.example.tabletrackfinal;

import android.content.Intent;
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
    // TODO: REPLACE THIS WITH YOUR ACTUAL STUDENT ID
    String STUDENT_ID = "10933286";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etUser = findViewById(R.id.usernameInput);
        etPass = findViewById(R.id.passwordInput);
        Button btnCust = findViewById(R.id.customerLoginButton);
        Button btnStaff = findViewById(R.id.staffLoginButton);
        ImageView btnHome = findViewById(R.id.home);
        ImageView btnSettings = findViewById(R.id.settings);
        TextView tvSignUp = findViewById(R.id.tvSignUp);

        btnCust.setOnClickListener(v -> login());
        btnStaff.setOnClickListener(v -> login());

        if (btnSettings != null) btnSettings.setOnClickListener(v -> startActivity(new Intent(this, settings.class)));
        if (btnHome != null) btnHome.setOnClickListener(v -> {
            startActivity(new Intent(LogIn.this, MainActivity.class));
            finish();
        });
        if (tvSignUp != null) tvSignUp.setOnClickListener(v -> startActivity(new Intent(LogIn.this, signUp.class)));
    }

    private void login() {
        String username = etUser.getText().toString().trim();
        String password = etPass.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Use the new getUser endpoint with STUDENT_ID
        RetrofitClient.getService().getUser(STUDENT_ID, username).enqueue(new Callback<UserResponse>() {
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

                        Toast.makeText(LogIn.this, "Welcome " + user.getFirstName(), Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(LogIn.this, "User not found (Check Student ID)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LogIn.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}