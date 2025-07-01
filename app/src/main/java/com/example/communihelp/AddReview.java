package com.example.communihelp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddReview extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText reviewDescription;
    private Button submitReviewBtn;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        ratingBar = findViewById(R.id.ratingBar);
        reviewDescription = findViewById(R.id.reviewDescription);
        submitReviewBtn = findViewById(R.id.submitReviewBtn);
        backArrow = findViewById(R.id.backArrow);

        submitReviewBtn.setOnClickListener(v -> {
            float stars = ratingBar.getRating();
            String desc = reviewDescription.getText().toString().trim();

            if (desc.isEmpty()) {
                Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Review Submitted\nStars: " + stars + "\n" + desc, Toast.LENGTH_SHORT).show();
                // You can send this data to your server or database
            }
        });

        backArrow.setOnClickListener(v -> finish());
    }
}
