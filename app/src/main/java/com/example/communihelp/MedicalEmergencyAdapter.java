package com.example.communihelp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicalEmergencyAdapter extends RecyclerView.Adapter<MedicalEmergencyAdapter.ViewHolder> {


    private List<MedicalEmergencyModule> medicalEmergencyList;
    public MedicalEmergencyAdapter(List<MedicalEmergencyModule> medicalEmergencyList) {
        this.medicalEmergencyList = medicalEmergencyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_productofferpage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalEmergencyAdapter.ViewHolder holder, int position) {

        MedicalEmergencyModule medicalEmergency = medicalEmergencyList.get(position);

        holder.tvDetails.setText(medicalEmergency.getDescription());
        holder.tvNameCategory.setText(medicalEmergency.getName()
        );

        holder.reviewText.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),ViewReviewPage.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    private String getStars(float rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        return stars.toString();
    }


    @Override
    public int getItemCount() {
        return medicalEmergencyList.size();
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
