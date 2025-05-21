package com.example.homeservices;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emazdoor6.R;
import com.example.homeservices.DateTimePickerDialog;

public class ServiceDetail extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServiceDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_detail);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewServiceDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Set up adapter
        adapter = new ServiceDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        
        // Show a toast to confirm the activity is loaded
        Toast.makeText(this, "Service Detail loaded", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}