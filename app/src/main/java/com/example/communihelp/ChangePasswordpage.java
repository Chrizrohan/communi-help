package com.example.communihelp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
;
import com.example.communihelp.server.ChangePasswordResponce;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordpage extends AppCompatActivity {

    EditText oldPassText, newPassText, confirmPassText;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwordpage);

        oldPassText = findViewById(R.id.oldpasswordtext);
        newPassText = findViewById(R.id.newpasswordtext);
        confirmPassText = findViewById(R.id.conformpasswordtext);

        userId = SharedPrefManager.getInstance(this).getUserId();

        findViewById(R.id.confirmchangepassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String oldPass = oldPassText.getText().toString().trim();
        String newPass = newPassText.getText().toString().trim();
        String confirmPass = confirmPassText.getText().toString().trim();

        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPass.equals(confirmPass)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getApiService();

        Call<ChangePasswordResponce> call = apiService.changePassword(userId, oldPass, newPass);
        call.enqueue(new Callback<ChangePasswordResponce>() {
            @Override
            public void onResponse(Call<ChangePasswordResponce> call, Response<ChangePasswordResponce> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ChangePasswordpage.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangePasswordpage.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponce> call, Throwable t) {
                Toast.makeText(ChangePasswordpage.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
