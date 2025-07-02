package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

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
                } else {
                    loadRequestHistory();
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
        // TODO: Fetch from API or local DB
        historyModelList.clear();
        historyModelList.add(new HistoryModel("1", "101", "Food", "Bread donation"));
        historyModelList.add(new HistoryModel("2", "101", "Water", "Water cans for society"));
        adapter.notifyDataSetChanged();
    }

    private void loadRequestHistory() {
        // TODO: Fetch from API or local DB
        historyModelList.clear();
        historyModelList.add(new HistoryModel("3", "101", "Medical", "Need first aid kit"));
        historyModelList.add(new HistoryModel("4", "101", "Clothes", "Blankets for kids"));
        adapter.notifyDataSetChanged();
    }
}
