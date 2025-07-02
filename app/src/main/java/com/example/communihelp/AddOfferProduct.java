package com.example.communihelp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.AddOfferProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOfferProduct extends AppCompatActivity {

    private RadioGroup categoryRadioGroup;
    private EditText detailsEditText;
    private Button addOfferButton;

    // You should get this dynamically from login/session data
    private String user_id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer_product);

        categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        detailsEditText = findViewById(R.id.detailsEditText);
        addOfferButton = findViewById(R.id.addofferproduct1);
        user_id = SharedPrefManager.getInstance(this).getUserId();

        addOfferButton.setOnClickListener(v -> {
            int selectedId = categoryRadioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String category = selectedRadio.getText().toString().trim();
            String details = detailsEditText.getText().toString().trim();

            if (details.isEmpty()) {
                Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
                return;
            }

            // API Call
            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            Call<AddOfferProductResponse> call = apiService.addOfferProduct(user_id, category, details);

            call.enqueue(new Callback<AddOfferProductResponse>() {
                @Override
                public void onResponse(Call<AddOfferProductResponse> call, Response<AddOfferProductResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().isStatus()) {
                            Toast.makeText(AddOfferProduct.this, "Offer added successfully", Toast.LENGTH_SHORT).show();
                            finish(); // Navigate back
                        } else {
                            Toast.makeText(AddOfferProduct.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddOfferProduct.this, "Server error. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddOfferProductResponse> call, Throwable t) {
                    Toast.makeText(AddOfferProduct.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
