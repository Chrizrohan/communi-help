package com.example.communihelp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up); // Ensure this matches your XML filename

        // Initialize the SIGN IN button
        signInButton = findViewById(R.id.sign); // Make sure this ID matches

        // If your button does NOT have an ID, assign one in XML:
        // android:id="@+id/signin_button"

        // Set click listener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Login activity
                Intent intent = new Intent(SignUp.this, Login.class); // Replace 'Login' with your actual login activity name
                startActivity(intent);
                finish(); // Optional: finish SignUp so user can't go back
            }
        });
    }
}
