package com.example.communihelp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    ImageButton homeIcon, historyIcon;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page); // Make sure this matches your layout file name

        // Initialize views
        homeIcon = findViewById(R.id.homeicon);
        historyIcon = findViewById(R.id.history);


        // Home button click -> go to HomePage
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ProfilePage.this, HomePage.class);
                startActivity(homeIntent);
                finish();
            }
        });

        // History button click -> go to HistoryActivity
        historyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(ProfilePage.this, HistoryActivity.class);
                startActivity(historyIntent);
                finish();
            }
        });

        // Optional: Back arrow to go back to HomePage
        backArrow.setOnClickListener(v -> {
            Intent backIntent = new Intent(ProfilePage.this, HomePage.class);
            startActivity(backIntent);
            finish();
        });
    }
}
