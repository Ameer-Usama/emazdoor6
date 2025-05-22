/* Services.java */
package com.example.emazdoor6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Services extends AppCompatActivity {
    private TextView titleText;
    private EditText searchInput;
    private ImageView searchIcon;
    private ProgressBar progressBar;
    private View noResultsView;
    private TextView searchTermText;

    private RecyclerView servicesRecyclerView;
    private ServiceAdapter serviceAdapter;
    private List<ServiceModel> serviceList;
    private List<ServiceModel> filteredList;
    private DatabaseReference databaseReference;
    private String tableName;
    private String serviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        //Intent Data Extraction
        Intent intent = getIntent();

        serviceName = intent.getStringExtra("Service");
        tableName = intent.getStringExtra("Category");
        titleText = findViewById(R.id.titleText);
        titleText.setText(serviceName);
        
        // Initialize UI components
        searchInput = findViewById(R.id.searchInput);
        searchIcon = findViewById(R.id.searchIcon);
        progressBar = findViewById(R.id.progressBar);
        
        // Initialize no results view components
        View noResultsLayout = findViewById(R.id.noResultsLayout);
        noResultsView = noResultsLayout.findViewById(R.id.noResultsView);
        searchTermText = noResultsLayout.findViewById(R.id.searchTermText);


        
        // Initialize RecyclerView
        servicesRecyclerView = findViewById(R.id.servicesRecyclerView);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(tableName);

        // Initialize service lists
        serviceList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Set up adapter
        serviceAdapter = new ServiceAdapter(this, filteredList);
        servicesRecyclerView.setAdapter(serviceAdapter);

        // Load data from Firebase
        loadServices();

        // Set up search functionality
        setupSearchFunctionality();
        
        // Set back button click listener
        findViewById(R.id.backButton).setOnClickListener(v -> onBackPressed());
    }

    private void loadServices() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceList.clear();

                for (DataSnapshot serviceSnapshot : snapshot.getChildren()) {
                    ServiceModel service = serviceSnapshot.getValue(ServiceModel.class);
                    if (service != null) {
                        serviceList.add(service);
                    }
                }
                
                // Initialize filtered list with all services
                filteredList.clear();
                filteredList.addAll(serviceList);
                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Services.this, "Failed to load services: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void setupSearchFunctionality() {
        // Set click listener for search icon
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchInput.getText().toString().trim().toLowerCase();
                if (!query.isEmpty()) {
                    performSearch(query);
                } else {
                    Toast.makeText(Services.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        // Add text change listener for automatic search after 3 characters
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim().toLowerCase();
                // Automatically search when user types at least 3 characters
                if (query.length() >= 3) {
                    performSearch(query);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // If search field is cleared, show all services
                if (s.toString().isEmpty()) {
                    filteredList.clear();
                    filteredList.addAll(serviceList);
                    serviceAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    
    private void performSearch(String query) {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);
        
        // Hide no results view initially
        if (noResultsView != null) {
            noResultsView.setVisibility(View.GONE);
        }
        servicesRecyclerView.setVisibility(View.VISIBLE);
        
        // Simulate network delay (remove in production)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Filter the service list based on query
                filteredList.clear();
                
                for (ServiceModel service : serviceList) {
                    if (service.getTitle().toLowerCase().contains(query) ||
                        (service.getDescription() != null && 
                         service.getDescription().toLowerCase().contains(query))) {
                        filteredList.add(service);
                    }
                }
                
                // Update the adapter
                serviceAdapter.notifyDataSetChanged();
                
                // Hide progress bar
                progressBar.setVisibility(View.GONE);
                
                // Show empty state if no results found
                if (filteredList.isEmpty()) {
                    servicesRecyclerView.setVisibility(View.GONE);
                    if (noResultsView != null) {
                        noResultsView.setVisibility(View.VISIBLE);
                    }
                    if (searchTermText != null) {
                        searchTermText.setText(query);
                    }
                } else {
                    servicesRecyclerView.setVisibility(View.VISIBLE);
                    if (noResultsView != null) {
                        noResultsView.setVisibility(View.GONE);
                    }
                }
            }
        }, 500); // 500ms delay to show progress bar (adjust as needed)
    }
}