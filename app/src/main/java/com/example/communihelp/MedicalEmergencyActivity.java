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

public class MedicalEmergencyActivity extends AppCompatActivity {

    private List<MedicalEmergencyModule> medicalEmergencyList;
    private RecyclerView recyclerView;
    private MedicalEmergencyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medical_emergency);


        medicalEmergencyList = new ArrayList<>();

        medicalEmergencyList.add(new MedicalEmergencyModule("Emergency 1", "Description 1", 4.5f));
        medicalEmergencyList.add(new MedicalEmergencyModule("Emergency 2", "Description 2", 3.8f));
        medicalEmergencyList.add(new MedicalEmergencyModule("Emergency 3", "Description 3", 4.2f));
        medicalEmergencyList.add(new MedicalEmergencyModule("Emergency 4", "Description 4", 4.0f));


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicalEmergencyAdapter(medicalEmergencyList);
        recyclerView.setAdapter(adapter);

    }
}