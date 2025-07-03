package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddRequest extends AppCompatActivity {

    TextView product, medical, service;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_request);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link UI elements
        product = findViewById(R.id.product);
        medical = findViewById(R.id.medical);
        service = findViewById(R.id.service);
        backArrow = findViewById(R.id.backArrow);

        // OnClick for Product → AddRequestProduct
        product.setOnClickListener(v -> {
            startActivity(new Intent(AddRequest.this, AddRequestProduct.class));
        });

        // OnClick for Medical Emergency → AddRequestMedicalEmergency
        medical.setOnClickListener(v -> {
            startActivity(new Intent(AddRequest.this, AddRequestMedicalEmergency.class));
        });

        // OnClick for Service → AddRequestService
        service.setOnClickListener(v -> {
            startActivity(new Intent(AddRequest.this, AddRequestService.class));
        });

        // OnClick for back arrow
        backArrow.setOnClickListener(v -> {
            onBackPressed(); // Navigate back
        });
    }
}
