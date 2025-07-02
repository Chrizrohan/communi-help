package com.example.communihelp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.EditProfileResponse;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePage extends AppCompatActivity {

    EditText cpName, cpEmail, cpAddress;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);

        cpName = findViewById(R.id.cpname);
        cpEmail = findViewById(R.id.cpemail);
        cpAddress = findViewById(R.id.cpaddress);




        userId = com.example.communihelp.SharedPrefManager.getInstance(this).getUserId();

        findViewById(R.id.confirmeditprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        String name = cpName.getText().toString().trim();
        String email = cpEmail.getText().toString().trim();
        String phone = cpAddress.getText().toString().trim(); // This is phone_number field

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getApiService();

        Call<EditProfileResponse> call = apiService.updateProfile(
                createRequestBody(userId),
                createRequestBody(name),
                createRequestBody(phone),
                createRequestBody(email)
        );

        call.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EditProfileResponse res = response.body();
                    Toast.makeText(EditProfilePage.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditProfilePage.this, "Server error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                Toast.makeText(EditProfilePage.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private RequestBody createRequestBody(String value) {
        return RequestBody.create(okhttp3.MultipartBody.FORM, value);
    }
}
