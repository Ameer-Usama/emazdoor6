package com.example.emazdoor6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




public class ServiceDetailAdapter extends RecyclerView.Adapter<ServiceDetailAdapter.ViewHolder> {
    Context context;

    public ServiceDetailAdapter(Context context, String rating, String name, String description, String amount) {
        this.context = context;
        Rating = rating;
        Name = name;
        Description = description;
        Amount = amount;
    }

    String Rating,Name,Description,Amount;



    @NonNull
    @Override
    public ServiceDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_detaail_adapter_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceDetailAdapter.ViewHolder holder, int position) {
        holder.serviceTitle.setText(Name);
        holder.totalPrice.setText(Amount);
        holder.tvRating.setText(Rating);
        if (holder.descriptionEditText != null && Description != null) {
            holder.descriptionEditText.setText(Description);
        }
        
        // Set back button click listener
        holder.backButton.setOnClickListener(v -> {
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRating, serviceTitle, totalPrice;
        EditText descriptionEditText;
        ImageButton backButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.tvRating);
            serviceTitle = itemView.findViewById(R.id.serviceTitle);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            descriptionEditText = itemView.findViewById(R.id.descriptionEditText);
            backButton = itemView.findViewById(R.id.backButton);
        }
    }
}
