package com.example.communihelp; // Replace with your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class FlashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screen); // This loads your splash screen layout

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // After the delay, start AuthActivity
            Intent intent = new Intent(FlashScreen.this, AuthActivity.class);
            startActivity(intent);
            finish(); // Close the splash screen so the user can't go back to it
        }, SPLASH_TIME);
    }
}
