package com.example.emazdoor6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    private ImageView btnBack;
    private ImageView btnEdit;
    private TextView tvUserName;
    private TextView tvUserEmail;
    
    // Menu items
    private View personalInfoLayout;
    private View languageLayout;
    private View privacyPolicyLayout;
    private View helpCenterLayout;
    private View settingLayout;
    private View logoutLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default selected item
        bottomNavigationView.setSelectedItemId(R.id.account_icon);

        // Handle navigation item clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home_icon) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.booking_icon) {
                Intent intent = new Intent(AccountActivity.this, BookingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.notification_icon) {
                Intent intent = new Intent(AccountActivity.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            } else if (itemId == R.id.account_icon) {
                // Already in AccountActivity
                return true;
            }
            return false;
        });
        
        // Initialize UI components
        initializeViews();
        
        // Set dummy data
        setDummyData();
        
        // Set click listeners
        setupClickListeners();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Ensure the home_icon is selected when this Activity is resumed
        bottomNavigationView.setSelectedItemId(R.id.account_icon);
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btn_back);
        btnEdit = findViewById(R.id.btn_edit);
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserEmail = findViewById(R.id.tv_user_email);
        
        // Menu items
        personalInfoLayout = findViewById(R.id.layout_personal_info);
        languageLayout = findViewById(R.id.layout_language);
        privacyPolicyLayout = findViewById(R.id.layout_privacy_policy);
        helpCenterLayout = findViewById(R.id.layout_help_center);
        settingLayout = findViewById(R.id.layout_setting);
        logoutLayout = findViewById(R.id.layout_logout);
    }
    
    private void setDummyData() {
        // Set dummy user data
        tvUserName.setText("Tom Ryan");
        tvUserEmail.setText("ryanlexx@gmail.com");
    }
    
    private void setupClickListeners() {
        // Back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to previous activity
            }
        });
        
        // Edit button click listener
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Edit profile clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Personal information click listener
        personalInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Personal information clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Language click listener
        languageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Language clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Privacy Policy click listener
        privacyPolicyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Privacy Policy clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Help center click listener
        helpCenterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Help center clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Setting click listener
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Setting clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        // Logout click listener
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                // Navigate to LoginActivity
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close AccountActivity
            }
        });
    }
}