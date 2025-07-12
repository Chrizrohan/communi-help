package com.example.communihelp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.Notify1ApiResponse;
import com.example.communihelp.server.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notify1Adapter extends RecyclerView.Adapter<Notify1Adapter.Notify1ViewHolder> {

    private final List<Notify1ApiResponse.Notify1Data> notifyList;

    public Notify1Adapter(List<Notify1ApiResponse.Notify1Data> notifyList) {
        this.notifyList = notifyList != null ? notifyList : new java.util.ArrayList<>();
    }

    @NonNull
    @Override
    public Notify1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notify1_item, parent, false);
        return new Notify1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notify1ViewHolder holder, int position) {
        Notify1ApiResponse.Notify1Data model = notifyList.get(position);

        holder.titleTextView.setText(model.accepted_by_name + " - " + model.category);
        holder.messageTextView.setText("Details: " + model.details);

        holder.yesTextView.setOnClickListener(v -> {
            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            Call<SimpleResponse> call = apiService.sendYesResponse(model.ref_id);

            call.enqueue(new Callback<SimpleResponse>() {
                @Override
                public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                    if (response.isSuccessful() ) {
                        if (response.body().isStatus()){
                            Toast.makeText(v.getContext(), "✅ " + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            notifyList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, notifyList.size());
                        }

                    } else {
                        Toast.makeText(v.getContext(), "❌ Failed to proceed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t) {
                    Toast.makeText(v.getContext(), "⚠️ Network error", Toast.LENGTH_SHORT).show();
                }
            });
        });

        holder.noTextView.setOnClickListener(v ->
                Toast.makeText(v.getContext(), "❌ NO clicked for " + model.accepted_by_name, Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return notifyList != null ? notifyList.size() : 0;
    }

    public static class Notify1ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, messageTextView, yesTextView, noTextView;

        public Notify1ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            yesTextView = itemView.findViewById(R.id.yesTextView);
            noTextView = itemView.findViewById(R.id.noTextView);
        }
    }
}
