package com.example.emazdoor6;

import android.app.Activity;
import android.content.Intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.emazdoor6.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    private CardView addressBarContainer;
    private ImageView ivLocation, ivDropdown;
    private TextView tvDeliveryAddressLabel, tvAddress, tvNotificationBadge;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default selected item
        bottomNavigationView.setSelectedItemId(R.id.home_icon);

        // Handle navigation item clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home_icon) {
                // Already in MainActivity
                return true;
            } else if (itemId == R.id.booking_icon) {
                Intent intent = new Intent(MainActivity.this, BookingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.notification_icon) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.account_icon) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });





        
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

    @Override
    protected void onResume() {
        super.onResume();
        // Ensure the booking_icon is selected when this Activity is resumed
        bottomNavigationView.setSelectedItemId(R.id.home_icon);
    }
    
    private void setupClickListeners() {
        // Address bar click listener

        
        // Set up category click listeners
        CardView seeAllCategory = findViewById(R.id.category_see_all);
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
        //For ac_repair Category
        CardView acRepairing = findViewById(R.id.category_ac_repair);
        sendingCategory(acRepairing,"ac_repair","AC Repair");
        //For category_beauty  appliances
        CardView beauty = findViewById(R.id.category_beauty);
        sendingCategory(beauty,"beauty","Beauty");

        CardView appliances = findViewById(R.id.appliances);
        sendingCategory(appliances,"appliance","Appliances");


    }
        //Function For Sending Category Information
        private void sendingCategory(CardView cardView,String tableName,String serviceName){

            if (cardView != null) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Navigate to CategoryActivity
                        Intent intent = new Intent(MainActivity.this, Services.class);
                        intent.putExtra("Service",serviceName);
                        intent.putExtra("Category",tableName);
                        startActivity(intent);
                    }
                });
            }

        }




}
