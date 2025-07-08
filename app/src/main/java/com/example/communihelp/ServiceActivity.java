package com.example.communihelp;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.ServiceResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServiceAdapter adapter;
    private List<ServiceResponse.ServiceData> serviceList;
    private Context context;
    private TabLayout tabLayout;
    private int selectedTabIndex = 0;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        context = this;
        userId = SharedPrefManager.getInstance(getBaseContext()).getUserId();
        if (userId == null) {
            Toast.makeText(this, "User ID not provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tabLayout = findViewById(R.id.tabLayout);

        serviceList = new ArrayList<>();
        adapter = new ServiceAdapter(serviceList, selectedTabIndex, userId);
        recyclerView.setAdapter(adapter);

        loadServiceOffers();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                selectedTabIndex = tab.getPosition();
                adapter = new ServiceAdapter(serviceList, selectedTabIndex, userId);
                recyclerView.setAdapter(adapter);

                if (selectedTabIndex == 0) {
                    loadServiceOffers();
                } else {
                    loadServiceRequests();
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadServiceOffers() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getServiceOffers().enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(context, "No service offers found", Toast.LENGTH_SHORT).show();
                    adapter.setData(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadServiceRequests() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getServiceRequests().enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(context, "No service requests found", Toast.LENGTH_SHORT).show();
                    adapter.setData(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

