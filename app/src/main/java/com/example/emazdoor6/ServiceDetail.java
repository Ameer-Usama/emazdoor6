package com.example.emazdoor6;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ServiceDetail extends AppCompatActivity {
    private String Rating,Name,Description,Amount;

    private RecyclerView recyclerView;
    private ServiceDetailAdapter serviceDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_detail);

        recyclerView = findViewById(R.id.recyclerViewServiceDetail);
        
        // Set up back button click listener in the adapter
        
        Intent intent = getIntent();

        Rating = intent.getStringExtra("rating");
        Name = intent.getStringExtra("name");
        Description = intent.getStringExtra("description");
        Amount = intent.getStringExtra("amount");

        recyclerView.setLayoutManager(new LinearLayoutManager(ServiceDetail.this));
        serviceDetailAdapter = new ServiceDetailAdapter(ServiceDetail.this,Rating,Name,Description,Amount);
        recyclerView.setAdapter(serviceDetailAdapter);
    }
}