package com.example.communihelp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.AddRequestProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRequestProduct extends AppCompatActivity {

    RadioGroup categoryGroup;
    EditText detailsEditText;
    Button submitButton;
    ImageView backArrow;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request_product);

        categoryGroup = findViewById(R.id.categoryRadioGroup);
        detailsEditText = findViewById(R.id.detailsEditText);
        submitButton = findViewById(R.id.addofferproduct1);
        backArrow = findViewById(R.id.backArrow);

        // Get user ID from Shared Preferences
        userId = SharedPrefManager.getInstance(this).getUserId();

        submitButton.setOnClickListener(view -> submitRequest());
        backArrow.setOnClickListener(v -> finish());
    }

    private void submitRequest() {
        int selectedId = categoryGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedButton = findViewById(selectedId);
        String category = selectedButton.getText().toString();
        String details = detailsEditText.getText().toString().trim();

        if (details.isEmpty()) {
            Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AddRequestProductResponse> call = apiService.addRequestProduct(userId, category, details);

        call.enqueue(new Callback<AddRequestProductResponse>() {
            @Override
            public void onResponse(Call<AddRequestProductResponse> call, Response<AddRequestProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddRequestProduct.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    finish(); // Go back to the previous page
                } else {
                    Toast.makeText(AddRequestProduct.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddRequestProductResponse> call, Throwable t) {
                Toast.makeText(AddRequestProduct.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
