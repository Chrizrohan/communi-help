package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {

    ImageButton homeIcon, historyIcon;
    EditText cpname, cpemail, cpaddress; // Text fields to display profile info

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize views
        homeIcon = findViewById(R.id.homeicon);
        historyIcon = findViewById(R.id.history);

        cpname = findViewById(R.id.profilename);       // Make sure your layout has these IDs
        cpemail = findViewById(R.id.profileemail);
        cpaddress = findViewById(R.id.profilephonenum);

        // Home button action
        homeIcon.setOnClickListener(v -> {
            Intent homeIntent = new Intent(ProfilePage.this, HomePage.class);
            startActivity(homeIntent);
            finish();
        });

        // History button action
        historyIcon.setOnClickListener(v -> {
            Intent historyIntent = new Intent(ProfilePage.this, HistoryActivity.class);
            startActivity(historyIntent);
            finish();
        });

        // Fetch user profile using Retrofit
        ApiService apiService = ApiClient.getApiService();
        String id = SharedPrefManager.getInstance(ProfilePage.this).getUserId();
        Call<ProfileResponse> call = apiService.getUserProfile(id); // Replace "1" with dynamic user ID if needed

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProfileResponse profile = response.body();
                    if (profile.isStatus()) {
                        ProfileResponse.UserData user = profile.getData();
                        cpname.setText(user.getUsername());
                        cpemail.setText(user.getEmail());
                        cpaddress.setText(user.getPhoneNumber()); // Replace with address if needed
                    } else {
                        Toast.makeText(ProfilePage.this, profile.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfilePage.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(ProfilePage.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
