package com.example.communihelp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private List<ServiceModule>servicelist;

    public ServiceAdapter(List<ServiceModule> servicelist) {
        this.servicelist = servicelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_productofferpage, parent, false);
        return new ViewHolder(view);
    }

    // ✅ This is the only correct onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       ServiceModule service = servicelist.get(position);

        holder.tvDetails.setText(service.getDescription());
        holder.tvNameCategory.setText(service.getName());

        holder.reviewText.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),ViewReviewPage.class);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return servicelist.size();
    }

    private String getStars(float rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        return stars.toString();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView  tvNameCategory, tvDetails,reviewText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            reviewText = itemView.findViewById(R.id.viewReview);
        }
    }
}
