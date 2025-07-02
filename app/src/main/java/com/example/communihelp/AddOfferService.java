package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.AddOfferServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOfferService extends AppCompatActivity {

    EditText detailsEditText;
    RadioGroup categoryGroup;
    Button addOfferButton;

    String userId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer_service);

        detailsEditText = findViewById(R.id.detailsEditText);
        categoryGroup = findViewById(R.id.categoryRadioGroup);
        addOfferButton = findViewById(R.id.addofferproduct1);
        userId = SharedPrefManager.getInstance(this).getUserId();// Note: reuse of ID from product

        addOfferButton.setOnClickListener(v -> {
            int selectedId = categoryGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String category = selectedRadio.getText().toString();
            String details = detailsEditText.getText().toString().trim();

            if (details.isEmpty()) {
                Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
                return;
            }

            sendServiceOfferToServer(userId, category, details);
        });
    }

    private void sendServiceOfferToServer(String userId, String category, String details) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AddOfferServiceResponse> call = apiService.addOfferService(userId, category, details);

        call.enqueue(new Callback<AddOfferServiceResponse>() {
            @Override
            public void onResponse(Call<AddOfferServiceResponse> call, Response<AddOfferServiceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddOfferService.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish(); // Go back
                } else {
                    Toast.makeText(AddOfferService.this, "Failed to submit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddOfferServiceResponse> call, Throwable t) {
                Toast.makeText(AddOfferService.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
