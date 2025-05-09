package com.example.emazdoor6;

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
    private ImageView ivLocation, ivDropdown, ivCart;
    private TextView tvDeliveryAddressLabel, tvAddress, tvNotificationBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Initialize UI components
        initializeViews();
        
        // Set up click listeners
        setupClickListeners();
        
        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    
    private void initializeViews() {
        // Address bar components
        addressBarContainer = findViewById(R.id.address_bar_container);
        ivLocation = findViewById(R.id.iv_location);
        ivDropdown = findViewById(R.id.iv_dropdown);
        tvDeliveryAddressLabel = findViewById(R.id.tv_delivery_address_label);
        tvAddress = findViewById(R.id.tv_address);
        

    }
    
    private void setupClickListeners() {
        // Address bar click listener
        addressBarContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Address selection clicked", Toast.LENGTH_SHORT).show();
                // TODO: Implement address selection functionality
            }
        });
        
        // Shopping cart click listener
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Shopping cart clicked", Toast.LENGTH_SHORT).show();
                // TODO: Implement shopping cart functionality
            }
        });
    }
}