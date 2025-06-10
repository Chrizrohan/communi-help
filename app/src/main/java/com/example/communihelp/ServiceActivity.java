package com.example.communihelp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    private List<ServiceModule> serviceList;
    private RecyclerView recyclerView;
    private ServiceAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medical_emergency);


        serviceList = new ArrayList<>();

        serviceList.add(new ServiceModule("Emergency 1", "Description 1", 4.5f));
        serviceList.add(new ServiceModule("Emergency 2", "Description 2", 3.8f));
        serviceList.add(new ServiceModule("Emergency 3", "Description 3", 4.2f));
        serviceList.add(new ServiceModule("Emergency 4", "Description 4", 4.0f));


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServiceAdapter(serviceList);
        recyclerView.setAdapter(adapter);

    }
}