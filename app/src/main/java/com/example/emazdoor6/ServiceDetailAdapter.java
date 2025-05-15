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

    // Variables to track unit and bedroom counts
    private int unitCount = 1; // Default value is 1
    private int bedroomCount = 0; // Default value is 0
    
    // Method to handle unit increment/decrement
    private void updateUnitCount(ViewHolder holder, boolean increment) {
        if (increment) {
            unitCount++;
        } else if (unitCount > 1) { // Prevent going below 1
            unitCount--;
        }
        
        // Update the UI
        holder.unitCount.setText(String.valueOf(unitCount));
        
        // Update button backgrounds
        updateCounterButtonBackground(holder.decrementUnits, holder.incrementUnits, increment);
    }
    
    // Method to handle bedroom increment/decrement
    private void updateBedroomCount(ViewHolder holder, boolean increment) {
        if (increment) {
            bedroomCount++;
        } else if (bedroomCount > 0) { // Prevent going below 0
            bedroomCount--;
        }
        
        // Update the UI
        holder.bedroomCount.setText(String.valueOf(bedroomCount));
        
        // Update button backgrounds
        updateCounterButtonBackground(holder.decrementBedrooms, holder.incrementBedrooms, increment);
    }
    
    // Helper method to update counter button backgrounds
    private void updateCounterButtonBackground(View decrementButton, View incrementButton, boolean isIncrement) {
        androidx.cardview.widget.CardView decrementCard = (androidx.cardview.widget.CardView) decrementButton;
        androidx.cardview.widget.CardView incrementCard = (androidx.cardview.widget.CardView) incrementButton;
        
        // Reset both buttons to default background
        decrementCard.setCardBackgroundColor(context.getResources().getColor(R.color.light_gray));
        incrementCard.setCardBackgroundColor(context.getResources().getColor(R.color.light_gray));
        
        // Set text color for both buttons
        TextView decrementText = (TextView) ((ViewGroup) decrementCard).getChildAt(0);
        TextView incrementText = (TextView) ((ViewGroup) incrementCard).getChildAt(0);
        decrementText.setTextColor(context.getResources().getColor(android.R.color.black));
        incrementText.setTextColor(context.getResources().getColor(android.R.color.black));
        
        // Highlight the button that was clicked
        if (isIncrement) {
            incrementCard.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
            incrementText.setTextColor(context.getResources().getColor(android.R.color.white));
        } else {
            decrementCard.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
            decrementText.setTextColor(context.getResources().getColor(android.R.color.white));
        }
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
        
        // Set default values for unit and bedroom counts
        holder.unitCount.setText(String.valueOf(unitCount));
        holder.bedroomCount.setText(String.valueOf(bedroomCount));
        
        // Set up unit counter buttons
        holder.decrementUnits.setOnClickListener(v -> updateUnitCount(holder, false));
        holder.incrementUnits.setOnClickListener(v -> updateUnitCount(holder, true));
        
        // Set up bedroom counter buttons
        holder.decrementBedrooms.setOnClickListener(v -> updateBedroomCount(holder, false));
        holder.incrementBedrooms.setOnClickListener(v -> updateBedroomCount(holder, true));
        
        // Set initial button states
        ((androidx.cardview.widget.CardView) holder.incrementUnits).setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
        TextView incrementUnitsText = (TextView) ((ViewGroup) holder.incrementUnits).getChildAt(0);
        incrementUnitsText.setTextColor(context.getResources().getColor(android.R.color.white));
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
        TextView unitCount, bedroomCount;
        View decrementUnits, incrementUnits, decrementBedrooms, incrementBedrooms;

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
            
            // Units counter elements
            unitCount = itemView.findViewById(R.id.unitCount);
            decrementUnits = itemView.findViewById(R.id.decrementUnits);
            incrementUnits = itemView.findViewById(R.id.incrementUnits);
            
            // Bedrooms counter elements
            bedroomCount = itemView.findViewById(R.id.bedroomCount);
            decrementBedrooms = itemView.findViewById(R.id.decrementBedrooms);
            incrementBedrooms = itemView.findViewById(R.id.incrementBedrooms);
        }
    }
}
