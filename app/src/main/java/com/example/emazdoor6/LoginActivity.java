package com.example.emazdoor6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin, btnSignup, btnGoogle, btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Removing EdgeToEdge.enable() as it can interfere with custom drawable rendering
        setContentView(R.layout.activity_login);
        
        // Initialize UI components
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        btnGoogle = findViewById(R.id.btn_google);
        btnFacebook = findViewById(R.id.btn_facebook);
        
        // Set click listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For demonstration purposes, show a toast message
                Toast.makeText(LoginActivity.this, "Login button clicked", Toast.LENGTH_SHORT).show();
                // Navigate to MainActivity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Sign Up button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Continue with Google clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Continue with Facebook clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}