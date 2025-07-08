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
import com.example.communihelp.server.ProductResponse;
import com.example.communihelp.server.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferProductAdapter extends RecyclerView.Adapter<OfferProductAdapter.OfferViewHolder> {

    private List<ProductResponse.ProductData> offerList;
    private int selectedTab; // 0 = Offer, 1 = Request
    private String userId;

    public OfferProductAdapter(List<ProductResponse.ProductData> offerList, int selectedTab, String userId) {
        this.offerList = offerList;
        this.selectedTab = selectedTab;
        this.userId = userId;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_productofferpage, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        ProductResponse.ProductData offer = offerList.get(position);

        holder.tvNameCategory.setText(offer.getUsername() + " : " + offer.getCategory());
        holder.tvDetails.setText("- " + offer.getDetails());

        // Accept button
        holder.btnAccept.setOnClickListener(v -> {
            ApiService apiService = ApiClient.getClient().create(ApiService.class);

            Call<SimpleResponse> call;
            if (selectedTab == 0) {
                call = apiService.acceptProductOffer(offer.getRef_id(), userId);
            } else {
                call = apiService.acceptProductRequest(offer.getRef_id(), userId);
            }

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                        Toast.makeText(holder.itemView.getContext(), "Accepted Successfully", Toast.LENGTH_SHORT).show();
                        offerList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Failed to accept", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Ignore button
        holder.btnIgnore.setOnClickListener(v -> {
            ApiService apiService = ApiClient.getClient().create(ApiService.class);

            Call<SimpleResponse> call;
            if (selectedTab == 0) {
                call = apiService.ignoreProductOffer(offer.getRef_id());
            } else {
                call = apiService.ignoreProductRequest(offer.getRef_id());
            }

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                        Toast.makeText(holder.itemView.getContext(), "Ignored Successfully", Toast.LENGTH_SHORT).show();
                        offerList.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Failed to ignore", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Review click
        holder.reviewText.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ViewReviewPage.class);
            intent.putExtra("user_id", offer.getUser_id());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    public void setData(List<ProductResponse.ProductData> newData) {
        this.offerList = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCategory, tvDetails;
        TextView btnAccept, btnIgnore, reviewText;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnIgnore = itemView.findViewById(R.id.btnIgnore);
            reviewText = itemView.findViewById(R.id.viewReview);
        }
    }
}
