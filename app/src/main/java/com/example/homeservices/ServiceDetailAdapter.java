package com.example.homeservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emazdoor6.R;
import com.example.homeservices.DateTimePickerDialog;

import java.util.Calendar;

public class ServiceDetailAdapter extends RecyclerView.Adapter<ServiceDetailAdapter.ViewHolder> {

    private Context context;
    private double servicePrice = 128.00; // Default price from layout

    public ServiceDetailAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_detaail_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set service details
        holder.serviceTitle.setText("AC Regular Service");
        holder.tvRating.setText("4.5");
        holder.totalPrice.setText(String.format("USD %.2f", servicePrice));

        // Set up click listeners
        holder.bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog();
            }
        });

        holder.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                if (context instanceof ServiceDetail) {
                    ((ServiceDetail) context).onBackPressed();
                }
            }
        });

        // Set up property type selection
        holder.homeOption.setOnClickListener(v -> selectPropertyType(holder, "home"));
        holder.officeOption.setOnClickListener(v -> selectPropertyType(holder, "office"));
        holder.villaOption.setOnClickListener(v -> selectPropertyType(holder, "villa"));

        // Set up unit counter
        holder.incrementUnits.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.unitCount.getText().toString());
            holder.unitCount.setText(String.valueOf(count + 1));
            updatePrice(holder);
        });

        holder.decrementUnits.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.unitCount.getText().toString());
            if (count > 1) {
                holder.unitCount.setText(String.valueOf(count - 1));
                updatePrice(holder);
            }
        });

        // Set up bedroom counter
        holder.incrementBedrooms.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.bedroomCount.getText().toString());
            holder.bedroomCount.setText(String.valueOf(count + 1));
        });

        holder.decrementBedrooms.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.bedroomCount.getText().toString());
            if (count > 0) {
                holder.bedroomCount.setText(String.valueOf(count - 1));
            }
        });
    }

    private void selectPropertyType(ViewHolder holder, String type) {
        // Reset all options
        holder.homeOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        holder.officeOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        holder.villaOption.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));

        // Set selected option
        switch (type) {
            case "home":
                holder.homeOption.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
                break;
            case "office":
                holder.officeOption.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
                break;
            case "villa":
                holder.villaOption.setCardBackgroundColor(context.getResources().getColor(R.color.purple_500));
                break;
        }
    }

    private void updatePrice(ViewHolder holder) {
        int units = Integer.parseInt(holder.unitCount.getText().toString());
        double newPrice = servicePrice * units;
        holder.totalPrice.setText(String.format("USD %.2f", newPrice));
    }

    private void showDateTimePickerDialog() {
        try {
            // First show a toast to confirm the button click is working
            Toast.makeText(context, "Opening booking dialog...", Toast.LENGTH_SHORT).show();
            
            // Create the dialog
            DateTimePickerDialog dialog = new DateTimePickerDialog(context);
            
            // Set the price
            dialog.setTotalPrice(servicePrice);
            
            // Set the listener for date/time selection
            dialog.setOnDateTimeSelectedListener(new DateTimePickerDialog.OnDateTimeSelectedListener() {
                @Override
                public void onDateTimeSelected(Calendar date, Calendar time) {
                    // Handle the selected date and time
                    Toast.makeText(context, "Booking confirmed for " + 
                            date.get(Calendar.DAY_OF_MONTH) + "/" + 
                            (date.get(Calendar.MONTH) + 1) + "/" + 
                            date.get(Calendar.YEAR) + " at " + 
                            time.get(Calendar.HOUR_OF_DAY) + ":" + 
                            time.get(Calendar.MINUTE), 
                            Toast.LENGTH_LONG).show();
                }
            });
            
            // Show the dialog
            dialog.show();
        } catch (Exception e) {
            // Log the error and show a toast message with detailed error information
            e.printStackTrace();
            Toast.makeText(context, "Error showing date time picker: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return 1; // Only one service detail item
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceTitle, tvRating, totalPrice;
        TextView unitCount, bedroomCount;
        Button bookNowButton, saveDraftButton;
        ImageButton backButton;
        CardView homeOption, officeOption, villaOption;
        View incrementUnits, decrementUnits, incrementBedrooms, decrementBedrooms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.serviceTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            bookNowButton = itemView.findViewById(R.id.bookNowButton);
            saveDraftButton = itemView.findViewById(R.id.saveDraftButton);
            backButton = itemView.findViewById(R.id.backButton);
            
            // Property type options
            homeOption = (CardView) itemView.findViewById(R.id.homeOption);
            officeOption = (CardView) itemView.findViewById(R.id.officeOption);
            villaOption = (CardView) itemView.findViewById(R.id.villaOption);
            
            // Unit counter
            unitCount = itemView.findViewById(R.id.unitCount);
            incrementUnits = itemView.findViewById(R.id.incrementUnits);
            decrementUnits = itemView.findViewById(R.id.decrementUnits);
            
            // Bedroom counter
            bedroomCount = itemView.findViewById(R.id.bedroomCount);
            incrementBedrooms = itemView.findViewById(R.id.incrementBedrooms);
            decrementBedrooms = itemView.findViewById(R.id.decrementBedrooms);
        }
    }
}