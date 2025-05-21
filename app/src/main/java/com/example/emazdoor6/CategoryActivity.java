package com.example.emazdoor6;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.Firebase;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {



    private EditText searchEditText;
    private List<View> categoryViews = new ArrayList<>();
    private List<String> categoryNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        
        // Set up back button
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and return to the previous one
            }
        });
        
        // Initialize search functionality
        setupSearchFunctionality();
        

        // Find all category views
        CardView acRepair = findViewById(R.id.category_ac_repair);
        CardView beauty = findViewById(R.id.category_beauty);
        CardView appliance = findViewById(R.id.category_appliance);
        CardView painting = findViewById(R.id.category_painting);
        CardView cleaning = findViewById(R.id.category_cleaning);
        CardView plumbing = findViewById(R.id.category_plumbing);
        CardView electronics = findViewById(R.id.category_electronics);
        CardView shifting = findViewById(R.id.category_shifting);
        CardView mensSalon = findViewById(R.id.category_mens_salon);


        sendingCategory(acRepair,"ac_repair","AC Repair");
        sendingCategory(beauty,"beauty","Beauty");
        sendingCategory(appliance,"appliance","Appliance");
        sendingCategory(painting,"painting","Painting");
        sendingCategory(cleaning,"cleaning","Cleaning");
        sendingCategory(plumbing,"plumbing","Plumbing");
        sendingCategory(electronics,"electronics","Electronics");
        sendingCategory(shifting,"shifting","Shifting");
        sendingCategory(mensSalon,"mens_salon","Men's Salon");

    }

    //Function For Sending Category Information
    private void sendingCategory(CardView cardView,String tableName,String serviceName){

        if (cardView != null) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to CategoryActivity
                    Intent intent = new Intent(CategoryActivity.this, Services.class);
                    intent.putExtra("Category",tableName);
                    intent.putExtra("Service",serviceName);
                    startActivity(intent);
                }
            });
        }

    }
    
    private void setupSearchFunctionality() {
        searchEditText = findViewById(R.id.et_search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCategories(s.toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }
    
    private void filterCategories(String query) {
        for (int i = 0; i < categoryViews.size(); i++) {
            View categoryView = categoryViews.get(i);
            String categoryName = categoryNames.get(i);
            
            if (query.isEmpty() || categoryName.toLowerCase().contains(query)) {
                categoryView.setVisibility(View.VISIBLE);
            } else {
                categoryView.setVisibility(View.GONE);
            }
        }
    }
    


}