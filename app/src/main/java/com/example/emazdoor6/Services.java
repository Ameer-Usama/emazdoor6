/* Services.java */
package com.example.emazdoor6;

import android.os.Bundle;

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

    private RecyclerView servicesRecyclerView;
    private ServiceAdapter serviceAdapter;
    private List<ServiceModel> serviceList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Initialize RecyclerView
        servicesRecyclerView = findViewById(R.id.servicesRecyclerView);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ac_repair");

        // Initialize service list
        serviceList = new ArrayList<>();

        // Set up adapter
        serviceAdapter = new ServiceAdapter(this, serviceList);
        servicesRecyclerView.setAdapter(serviceAdapter);

        // Load data from Firebase
        loadServices();

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

                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });
    }
}