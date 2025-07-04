package com.example.communihelp;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.ProductResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferProduct extends AppCompatActivity {

    RecyclerView recyclerViewOffers;
    OfferProductAdapter adapter;
    List<ProductResponse.ProductData> offerList;

    Context context;
    TabLayout tabLayout;
    private int selectedTabIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerproduct);

        context = this;

        recyclerViewOffers = findViewById(R.id.recyclerViewOffers);
        recyclerViewOffers.setLayoutManager(new LinearLayoutManager(this));
        tabLayout = findViewById(R.id.tabLayout);

        // Dummy data
        offerList = new ArrayList<>();


        adapter = new OfferProductAdapter(offerList);
        recyclerViewOffers.setAdapter(adapter);

        loadProductOffer();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabIndex = tab.getPosition();
                if (selectedTabIndex == 0) {
                    loadProductOffer();

                } else {
                    loadProductReqOffer();

                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

    }

    private void loadProductReqOffer() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        apiService.getProductRequests().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(context, "No offers found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void loadProductOffer(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        apiService.getProductOffers().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    adapter.setData(response.body().getData());
                } else {
                    Toast.makeText(context, "No offers found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(context, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
