package com.example.emazdoor6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private Context context;
    private List<ServiceModel> serviceList;

    public ServiceAdapter(Context context, List<ServiceModel> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceModel service = serviceList.get(position);

        holder.serviceTitle.setText(service.getTitle());
        holder.ratingText.setText(String.format(Locale.getDefault(), "%.1f (%d)",
                service.getRating(), service.getReviews()));
        holder.servicePrice.setText(String.format(Locale.getDefault(), "$%.0f", service.getPrice()));

        // Load image with Glide
        if (service.getImageUrl() != null && !service.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(service.getImageUrl())
                    .placeholder(R.drawable.placeholder_gray)
                    .error(R.drawable.placeholder_gray)
                    .into(holder.serviceImage);
        }

        // Set click listeners
        holder.itemView.setOnClickListener(v -> {
            // Handle item click
        });

        holder.moreButton.setOnClickListener(v -> {
            // Handle more button click
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImage, moreButton;
        TextView serviceTitle, ratingText, servicePrice;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImage = itemView.findViewById(R.id.serviceImage);
            moreButton = itemView.findViewById(R.id.moreButton);
            serviceTitle = itemView.findViewById(R.id.serviceTitle);
            ratingText = itemView.findViewById(R.id.ratingText);
            servicePrice = itemView.findViewById(R.id.servicePrice);
        }
    }
}