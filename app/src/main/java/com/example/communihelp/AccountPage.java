package com.example.communihelp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AccountPage extends AppCompatActivity {

    TextView editProfileText, changePasswordText;
    ImageView backArrow; // optional if you want to go back to SettingPage or HomePage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountpage); // replace with your layout file name

        // Initialize Views
        editProfileText = findViewById(R.id.editprofile);
        changePasswordText = findViewById(R.id.changepassword);
        backArrow = findViewById(R.id.backArrow);

        // Edit Profile click
        editProfileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountPage.this, EditProfilePage.class);
                startActivity(intent);
            }
        });

        // Change Password click
        changePasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountPage.this, ChangePasswordpage.class);
                startActivity(intent);
            }
        });

        // Optional: Back to Settings
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountPage.this, SettingPage.class);
                startActivity(intent);
                finish(); // closes current page
            }
        });
    }
}
