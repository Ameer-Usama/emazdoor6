package com.example.emazdoor6;

import android.content.Intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CardView addressBarContainer;
    private ImageView ivLocation, ivDropdown;
    private TextView tvDeliveryAddressLabel, tvAddress, tvNotificationBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable EdgeToEdge for proper layout rendering
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        

        
        // Set up click listeners
        setupClickListeners();
        
        // Add navigation to booking activity
        View acServiceView = findViewById(R.id.offer_ac_service);
        if (acServiceView != null) {
            acServiceView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, BookingActivity.class);
                startActivity(intent);
            });
        }
        
        // Simplified window insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // Force layout refresh
        findViewById(R.id.main).requestLayout();
    }
    

    
    private void setupClickListeners() {
        // Address bar click listener

        
        // Set up category click listeners
        View seeAllCategory = findViewById(R.id.category_see_all);
        if (seeAllCategory != null) {
            seeAllCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to CategoryActivity
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
            });
        }
        
        // Set up account navigation

        }
    }
