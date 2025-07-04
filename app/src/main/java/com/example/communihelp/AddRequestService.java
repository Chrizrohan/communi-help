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
import com.example.communihelp.server.AddRequestServiceResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRequestService extends AppCompatActivity {

    RadioGroup categoryGroup;
    EditText detailsEditText;
    Button submitButton;

    String userId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request_service);

        categoryGroup = findViewById(R.id.categoryRadioGroup);
        detailsEditText = findViewById(R.id.detailsEditText);
        submitButton = findViewById(R.id.addofferproduct1);
        userId = SharedPrefManager.getInstance(this).getUserId();

        submitButton.setOnClickListener(v -> submitRequest());
    }

    private void submitRequest() {
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

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AddRequestServiceResponse> call = apiService.addServiceRequest(userId, category, details);

        call.enqueue(new Callback<AddRequestServiceResponse>() {
            @Override
            public void onResponse(Call<AddRequestServiceResponse> call, Response<AddRequestServiceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddRequestService.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddRequestService.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddRequestServiceResponse> call, Throwable t) {
                Toast.makeText(AddRequestService.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
 