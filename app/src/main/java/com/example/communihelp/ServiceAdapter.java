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
import com.example.communihelp.server.ServiceResponse;
import com.example.communihelp.server.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private List<ServiceResponse.ServiceData> serviceList;
    private int selectedTab; // 0 = offer, 1 = request
    private String userId;

    public ServiceAdapter(List<ServiceResponse.ServiceData> serviceList, int selectedTab, String userId) {
        this.serviceList = serviceList;
        this.selectedTab = selectedTab;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_productofferpage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceResponse.ServiceData service = serviceList.get(position);

        holder.tvNameCategory.setText(service.getUsername() + " : " + service.getCategory());
        holder.tvDetails.setText("- " + service.getDetails());

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        holder.btnAccept.setOnClickListener(v -> {
            Call<SimpleResponse> call = selectedTab == 0
                    ? apiService.acceptServiceOffer(service.getRef_id(), userId)
                    : apiService.acceptServiceRequest(service.getRef_id(), userId);

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() && response.body().isStatus()) {
                        Toast.makeText(holder.itemView.getContext(), "Accepted Successfully", Toast.LENGTH_SHORT).show();
                        serviceList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Accept Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        holder.btnIgnore.setOnClickListener(v -> {
            Call<SimpleResponse> call = selectedTab == 0
                    ? apiService.ignoreServiceOffer(service.getRef_id())
                    : apiService.ignoreServiceRequest(service.getRef_id());

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() && response.body().isStatus()) {
                        Toast.makeText(holder.itemView.getContext(), "Ignored Successfully", Toast.LENGTH_SHORT).show();
                        serviceList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Ignore Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        holder.reviewText.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ViewReviewPage.class);
            intent.putExtra("user_id", service.getUser_id());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
    }

    public void setData(List<ServiceResponse.ServiceData> newList) {
        this.serviceList = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCategory, tvDetails, btnAccept, btnIgnore, reviewText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnIgnore = itemView.findViewById(R.id.btnIgnore);
            reviewText = itemView.findViewById(R.id.viewReview);
        }
    }
}
