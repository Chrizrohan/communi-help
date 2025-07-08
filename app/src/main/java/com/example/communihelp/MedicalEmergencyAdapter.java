package com.example.communihelp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.MedicalEmergencyResponse;
import com.example.communihelp.server.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalEmergencyAdapter extends RecyclerView.Adapter<MedicalEmergencyAdapter.ViewHolder> {

    private List<MedicalEmergencyResponse.MedicalData> medicalEmergencyList;
    private int selectedTab;
    private String userId;

    public MedicalEmergencyAdapter(List<MedicalEmergencyResponse.MedicalData> list, int selectedTab, String userId) {
        this.medicalEmergencyList = list;
        this.selectedTab = selectedTab;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_productofferpage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicalEmergencyResponse.MedicalData item = medicalEmergencyList.get(position);

        holder.tvDetails.setText(item.getDetails());
        holder.tvNameCategory.setText(item.getUsername());

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Accept button
        holder.btnAccept.setOnClickListener(v -> {
            Call<SimpleResponse> call;
            if (selectedTab == 0) {
                call = apiService.acceptMedicalOffer(item.getRef_id(), userId);
            } else {
                call = apiService.acceptMedicalRequest(item.getRef_id(), userId);
            }

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                        Toast.makeText(holder.itemView.getContext(), "Accepted Successfully", Toast.LENGTH_SHORT).show();
                        medicalEmergencyList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Accept Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Ignore button
        holder.btnIgnore.setOnClickListener(v -> {
            Call<SimpleResponse> call;
            if (selectedTab == 0) {
                call = apiService.ignoreMedicalOffer(item.getRef_id());
            } else {
                call = apiService.ignoreMedicalRequest(item.getRef_id());
            }

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                        Toast.makeText(holder.itemView.getContext(), "Ignored Successfully", Toast.LENGTH_SHORT).show();
                        medicalEmergencyList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Ignore Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Review click
        holder.reviewText.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ViewReviewPage.class);
            intent.putExtra("user_id", item.getUser_id());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return medicalEmergencyList.size();
    }

    public void setData(List<MedicalEmergencyResponse.MedicalData> newData) {
        this.medicalEmergencyList = newData;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameCategory, tvDetails, reviewText, btnAccept, btnIgnore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            reviewText = itemView.findViewById(R.id.viewReview);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnIgnore = itemView.findViewById(R.id.btnIgnore); // Make sure this is in XML
        }
    }
}
