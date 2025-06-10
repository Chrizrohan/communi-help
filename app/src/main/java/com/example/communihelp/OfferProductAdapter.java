package com.example.communihelp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.OfferProuductModule;
import com.example.communihelp.R;

import java.util.List;

public class OfferProductAdapter extends RecyclerView.Adapter<OfferProductAdapter.OfferViewHolder> {

    private List<OfferProuductModule> offerList;

    public OfferProductAdapter(List<OfferProuductModule> offerList) {
        this.offerList = offerList;
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
        OfferProuductModule offer = offerList.get(position);

        holder.tvNameCategory.setText(offer.getName() + " : " + offer.getCategory());
        holder.tvDetails.setText("- " + offer.getSubCategory());
        holder.tvStars.setText(getStars(offer.getRating()));

        // Optional: Button click actions
        holder.btnAccept.setOnClickListener(v -> {
            // Handle accept logic here
        });

        holder.btnIgnore.setOnClickListener(v -> {
            // Handle ignore logic here
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    private String getStars(float rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        return stars.toString();
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView tvStars, tvNameCategory, tvDetails;
        Button btnAccept, btnIgnore;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStars = itemView.findViewById(R.id.tvStars);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnIgnore = itemView.findViewById(R.id.btnIgnore);
        }
    }
}
