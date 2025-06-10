package com.example.communihelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth); // Links to your XML

        // Initialize buttons
        signupButton = findViewById(R.id.signup1); // This is the LOGIN button in XML
        loginButton = findViewById(R.id.login1);   // This is the SIGN IN button in XML

        // LOGIN button → SignupActivity
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        // SIGN IN button → LoginActivity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
