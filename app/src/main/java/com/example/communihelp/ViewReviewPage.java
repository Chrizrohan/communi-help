package com.example.communihelp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewReviewPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewReviewAdapter adapter;
    private List<ViewReviewModel> reviewList;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review_page); // Make sure this matches your layout file name

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewreview); // Make sure you added this in your layout
        backArrow = findViewById(R.id.backArrow);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare dummy data
        reviewList = new ArrayList<>();
        reviewList.add(new ViewReviewModel("Alice", "Clothes", "Very helpful and quick", "★★★★★"));
        reviewList.add(new ViewReviewModel("Bob", "Books", "Good donation", "★★★★☆"));
        reviewList.add(new ViewReviewModel("Charlie", "Toys", "Average experience", "★★★☆☆"));

        // Set adapter
        adapter = new ViewReviewAdapter(this, reviewList);
        recyclerView.setAdapter(adapter);

        // Handle back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the activity and goes back
            }
        });
    }
}
