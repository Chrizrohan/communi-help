package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.HistoryResponse;
import com.example.communihelp.server.ProductResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private List<HistoryModel> historyModelList;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private TabLayout tabLayout;
    private Button addOfferButton;

    // Track selected tab (0 = Offers, 1 = Requests)
    private int selectedTabIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        tabLayout = findViewById(R.id.tabLayout);
        addOfferButton = findViewById(R.id.addoffer);

        historyModelList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(historyModelList);
        recyclerView.setAdapter(adapter);

        // Default load for "Offers"
        loadOfferHistory();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabIndex = tab.getPosition();
                if (selectedTabIndex == 0) {
                    loadOfferHistory();
                    addOfferButton.setText("ADD Offer");
                } else {
                    loadRequestHistory();
                    addOfferButton.setText("ADD Request");
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        addOfferButton.setOnClickListener(v -> {
            if (selectedTabIndex == 0) {
                // Offers tab selected → go to AddOffer activity
                startActivity(new Intent(HistoryActivity.this, AddOffer.class));
            } else {
                // Requests tab selected → go to AddRequest activity
                startActivity(new Intent(HistoryActivity.this, AddRequest.class));
            }
        });
    }

    private void loadOfferHistory() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        apiService.getOfferHistory(SharedPrefManager.getInstance(HistoryActivity.this).getUserId()).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<HistoryResponse> call, @NonNull Response<HistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(HistoryActivity.this, "No offers found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRequestHistory() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        apiService.getRequestHistory(SharedPrefManager.getInstance(HistoryActivity.this).getUserId()).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<HistoryResponse> call, @NonNull Response<HistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(HistoryActivity.this, "No offers found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
