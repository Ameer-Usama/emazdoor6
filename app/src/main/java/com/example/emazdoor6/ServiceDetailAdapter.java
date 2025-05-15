package com.example.emazdoor6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    
    // Method to handle property type selection
    private void setupPropertyTypeSelection(ViewHolder holder) {
        // Set click listeners for property type options
        holder.homeOption.setOnClickListener(v -> selectPropertyType(holder, "home"));
        holder.officeOption.setOnClickListener(v -> selectPropertyType(holder, "office"));
        holder.villaOption.setOnClickListener(v -> selectPropertyType(holder, "villa"));
    }
    
    // Method to update UI based on selected property type
    private void selectPropertyType(ViewHolder holder, String type) {
        // Get the CardView objects directly
        androidx.cardview.widget.CardView homeCard = (androidx.cardview.widget.CardView) holder.homeOption;
        androidx.cardview.widget.CardView officeCard = (androidx.cardview.widget.CardView) holder.officeOption;
        androidx.cardview.widget.CardView villaCard = (androidx.cardview.widget.CardView) holder.villaOption;
        
        // Reset all options to default background
        homeCard.setCardBackgroundColor(context.getResources().getColor(R.color.light_gray));
        officeCard.setCardBackgroundColor(context.getResources().getColor(R.color.light_gray));
        villaCard.setCardBackgroundColor(context.getResources().getColor(R.color.light_gray));
        
        // Find all TextViews and ImageViews in each card and reset their colors
        updateCardChildrenColors(homeCard, false);
        updateCardChildrenColors(officeCard, false);
        updateCardChildrenColors(villaCard, false);
        
        // Set selected card background and text colors
        if (type.equals("home")) {
            homeCard.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
            updateCardChildrenColors(homeCard, true);
        } else if (type.equals("office")) {
            officeCard.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
            updateCardChildrenColors(officeCard, true);
        } else if (type.equals("villa")) {
            villaCard.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
            updateCardChildrenColors(villaCard, true);
        }
    }
    
    // Helper method to update colors of all children in a card
    private void updateCardChildrenColors(ViewGroup viewGroup, boolean isSelected) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            
            if (child instanceof ViewGroup) {
                // Recursively update colors for nested ViewGroups
                updateCardChildrenColors((ViewGroup) child, isSelected);
            } else if (child instanceof TextView) {
                // Update TextView color
                ((TextView) child).setTextColor(context.getResources().getColor(
                    isSelected ? android.R.color.white : android.R.color.black));
            } else if (child instanceof ImageView) {
                // Update ImageView tint
                if (isSelected) {
                    ((ImageView) child).setColorFilter(context.getResources().getColor(android.R.color.white));
                } else {
                    ((ImageView) child).clearColorFilter();
                }
            }
        }
    }



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
        
        // Set property type selection listeners
        holder.homeOption.setOnClickListener(v -> selectPropertyType(holder, "home"));
        holder.officeOption.setOnClickListener(v -> selectPropertyType(holder, "office"));
        holder.villaOption.setOnClickListener(v -> selectPropertyType(holder, "villa"));
        
        // Set office as default selected
        selectPropertyType(holder, "office");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRating, serviceTitle, totalPrice;
        EditText descriptionEditText;
        ImageButton backButton;
        View homeOption, officeOption, villaOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.tvRating);
            serviceTitle = itemView.findViewById(R.id.serviceTitle);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            descriptionEditText = itemView.findViewById(R.id.descriptionEditText);
            backButton = itemView.findViewById(R.id.backButton);
            
            // Property type options
            homeOption = itemView.findViewById(R.id.homeOption);
            officeOption = itemView.findViewById(R.id.officeOption);
            villaOption = itemView.findViewById(R.id.villaOption);
        }
    }
}
