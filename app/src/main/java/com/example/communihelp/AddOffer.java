package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddOffer extends AppCompatActivity {

    TextView product, medical, service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_offer);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Reference to TextViews
        product = findViewById(R.id.product);
        medical = findViewById(R.id.medical);
        service = findViewById(R.id.service);

        // Set Click Listeners
        product.setOnClickListener(v -> {
            Intent intent = new Intent(AddOffer.this, AddOfferProduct.class);
            startActivity(intent);
        });

        medical.setOnClickListener(v -> {
            Intent intent = new Intent(AddOffer.this, AddOfferMedicalEmergency.class);
            startActivity(intent);
        });

        service.setOnClickListener(v -> {
            Intent intent = new Intent(AddOffer.this, AddOfferService.class);
            startActivity(intent);
        });
    }
}
