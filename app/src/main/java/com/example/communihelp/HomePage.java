package com.example.communihelp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    ImageButton settingsButton, notificationButton, homeIcon, historyIcon, profileIcon;
    TextView productTextView, medicalTextView, serviceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize Views
        settingsButton = findViewById(R.id.settings_button);
        notificationButton = findViewById(R.id.notification_button);
        productTextView = findViewById(R.id.product);
        medicalTextView = findViewById(R.id.medical);
        serviceTextView = findViewById(R.id.service);
        homeIcon = findViewById(R.id.homeicon);
        historyIcon = findViewById(R.id.history);
        profileIcon = findViewById(R.id.profileicon);

        // Set Click Listeners
        settingsButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, SettingPage.class)));

        notificationButton.setOnClickListener(v -> startActivity(new Intent(HomePage.this, NotifyPage.class)));

        productTextView.setOnClickListener(v -> startActivity(new Intent(HomePage.this,OfferProduct.class)));

        medicalTextView.setOnClickListener(v -> startActivity(new Intent(HomePage.this,MedicalEmergencyActivity.class)));

        serviceTextView.setOnClickListener(v -> startActivity(new Intent(HomePage.this, ServiceActivity.class)));

        homeIcon.setOnClickListener(v -> startActivity(new Intent(HomePage.this, HomePage.class)));

        historyIcon.setOnClickListener(v -> startActivity(new Intent(HomePage.this, HistoryActivity.class)));

        profileIcon.setOnClickListener(v -> startActivity(new Intent(HomePage.this, ProfilePage.class)));
    }
}
