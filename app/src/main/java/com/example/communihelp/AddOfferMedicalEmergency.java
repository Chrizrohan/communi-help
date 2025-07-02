package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.AddOfferMedicalEmergencyResponse;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOfferMedicalEmergency extends AppCompatActivity {

    private RadioGroup categoryRadioGroup;
    private EditText detailsEditText;
    private String userId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer_medical_emergency);

        categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        detailsEditText = findViewById(R.id.detailsEditText);
        userId = SharedPrefManager.getInstance(this).getUserId();

        findViewById(R.id.addmedicalemergency1).setOnClickListener(view -> {
            int selectedId = categoryRadioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Select a category", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadioButton = findViewById(selectedId);
            String category = selectedRadioButton.getText().toString();
            String details = detailsEditText.getText().toString().trim();

            if (details.isEmpty()) {
                Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show();
                return;
            }

            sendMedicalOfferToServer(userId, category, details);
        });
    }

    private void sendMedicalOfferToServer(String userId, String category, String details) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AddOfferMedicalEmergencyResponse> call = apiService.addMedicalEmergencyOffer(userId, category, details);

        call.enqueue(new Callback<AddOfferMedicalEmergencyResponse>() {
            @Override
            public void onResponse(Call<AddOfferMedicalEmergencyResponse> call, Response<AddOfferMedicalEmergencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isStatus()) {
                        Toast.makeText(AddOfferMedicalEmergency.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish(); // Go back
                    } else {
                        Toast.makeText(AddOfferMedicalEmergency.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(AddOfferMedicalEmergency.this, ""+errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(AddOfferMedicalEmergency.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddOfferMedicalEmergencyResponse> call, Throwable t) {
                Toast.makeText(AddOfferMedicalEmergency.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
