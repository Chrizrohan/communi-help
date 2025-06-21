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
import androidx.appcompat.widget.AppCompatButton;

public class SettingPage extends AppCompatActivity {

    TextView accountText, helpText, aboutText;
    AppCompatButton exitButton;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page); // Replace with your actual layout file name

        // Initialize Views
        accountText = findViewById(R.id.account);
        helpText = findViewById(R.id.help);
        aboutText = findViewById(R.id.about);
        exitButton = findViewById(R.id.exitfromcommunity);
        backArrow = findViewById(R.id.backArrow);

        // Click to go to AccountPage
        accountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage.this, AccountPage.class));
            }
        });

        // Click to go to HelpPage
        helpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage.this, Helppage.class));
            }
        });

        // Click to go to AboutPage
        aboutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage.this, AboutPage.class));
            }
        });

        // Click to exit to AuthPage
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingPage.this, AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Optional: clears back stack
                startActivity(intent);
            }
        });

        // Click to go back to HomePage
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage.this, HomePage.class));
                finish(); // Optional: close current activity
            }
        });
    }
}
