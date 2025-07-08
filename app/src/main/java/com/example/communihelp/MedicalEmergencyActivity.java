package com.example.communihelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.MedicalEmergencyResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalEmergencyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedicalEmergencyAdapter adapter;
    private List<MedicalEmergencyResponse.MedicalData> medicalList;
    private Context context;
    private TabLayout tabLayout;
    private int selectedTabIndex = 0;
    private String userId; // To store user ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_emergency);

        context = this;

        // Get userId from Intent
        userId = SharedPrefManager.getInstance(getBaseContext()).getUserId();

        if (userId == null) {
            Toast.makeText(this, "User ID not provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tabLayout = findViewById(R.id.tabLayout);

        medicalList = new ArrayList<>();
        adapter = new MedicalEmergencyAdapter(medicalList, selectedTabIndex, userId); // ✅ Correct constructor usage
        recyclerView.setAdapter(adapter);

        loadMedicalOffers(); // Default tab (offers)

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabIndex = tab.getPosition();
                adapter = new MedicalEmergencyAdapter(medicalList, selectedTabIndex, userId);
                recyclerView.setAdapter(adapter);

                if (selectedTabIndex == 0) {
                    loadMedicalOffers();
                } else {
                    loadMedicalRequests();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadMedicalOffers() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getMedicalOffers().enqueue(new Callback<MedicalEmergencyResponse>() {
            @Override
            public void onResponse(Call<MedicalEmergencyResponse> call, Response<MedicalEmergencyResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(context, "No offers found", Toast.LENGTH_SHORT).show();
                    adapter.setData(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<MedicalEmergencyResponse> call, Throwable t) {
                Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMedicalRequests() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getMedicalRequests().enqueue(new Callback<MedicalEmergencyResponse>() {
            @Override
            public void onResponse(Call<MedicalEmergencyResponse> call, Response<MedicalEmergencyResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(context, "No requests found", Toast.LENGTH_SHORT).show();
                    adapter.setData(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<MedicalEmergencyResponse> call, Throwable t) {
                Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
