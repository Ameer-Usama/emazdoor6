package com.example.emazdoor6;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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
        
        // Set up category click listeners
        setupCategoryClickListeners();
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
    
    private void setupCategoryClickListeners() {
        // Find all category views
        View acRepair = findViewById(R.id.category_ac_repair);
        View beauty = findViewById(R.id.category_beauty);
        View appliance = findViewById(R.id.category_appliance);
        View painting = findViewById(R.id.category_painting);
        View cleaning = findViewById(R.id.category_cleaning);
        View plumbing = findViewById(R.id.category_plumbing);
        View electronics = findViewById(R.id.category_electronics);
        View shifting = findViewById(R.id.category_shifting);
        View mensSalon = findViewById(R.id.category_mens_salon);
        
        // Add all views to the list for filtering
        categoryViews.add(acRepair);
        categoryViews.add(beauty);
        categoryViews.add(appliance);
        categoryViews.add(painting);
        categoryViews.add(cleaning);
        categoryViews.add(plumbing);
        categoryViews.add(electronics);
        categoryViews.add(shifting);
        categoryViews.add(mensSalon);
        
        // Add category names for search filtering
        categoryNames.add("ac repair");
        categoryNames.add("beauty");
        categoryNames.add("appliance");
        categoryNames.add("painting");
        categoryNames.add("cleaning");
        categoryNames.add("plumbing");
        categoryNames.add("electronics");
        categoryNames.add("shifting");
        categoryNames.add("men's salon");
        
        // Set click listeners for each category
        View.OnClickListener categoryClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the index of the clicked category
                int index = categoryViews.indexOf(v);
                String categoryName = categoryNames.get(index);
                
                // Show a toast message for now (you can replace with actual navigation)
                android.widget.Toast.makeText(CategoryActivity.this, 
                        "Selected category: " + categoryName, 
                        android.widget.Toast.LENGTH_SHORT).show();
                
                // TODO: Navigate to specific service listing for the selected category
                // Intent intent = new Intent(CategoryActivity.this, ServiceListingActivity.class);
                // intent.putExtra("CATEGORY_NAME", categoryName);
                // startActivity(intent);
            }
        };
        
        // Apply the same click listener to all categories
        acRepair.setOnClickListener(categoryClickListener);
        beauty.setOnClickListener(categoryClickListener);
        appliance.setOnClickListener(categoryClickListener);
        painting.setOnClickListener(categoryClickListener);
        cleaning.setOnClickListener(categoryClickListener);
        plumbing.setOnClickListener(categoryClickListener);
        electronics.setOnClickListener(categoryClickListener);
        shifting.setOnClickListener(categoryClickListener);
        mensSalon.setOnClickListener(categoryClickListener);
    }
}