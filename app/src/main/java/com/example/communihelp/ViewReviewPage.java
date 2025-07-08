package com.example.communihelp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communihelp.api.ApiClient;
import com.example.communihelp.api.ApiService;
import com.example.communihelp.server.ReviewResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewReviewPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewReviewAdapter adapter;
    private List<ViewReviewModel> reviewList;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review_page);

        recyclerView = findViewById(R.id.recyclerViewreview);
        backArrow = findViewById(R.id.backArrow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewList = new ArrayList<>();
        adapter = new ViewReviewAdapter(this, reviewList);
        recyclerView.setAdapter(adapter);

        String userId = getIntent().getStringExtra("user_id");
        if (userId != null) {
            fetchReviews(userId);
        }

        backArrow.setOnClickListener(v -> finish());
    }

    private void fetchReviews(String userId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getReviews(userId).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    List<ReviewResponse.ReviewData> data = response.body().getData();
                    reviewList.clear();
                    for (ReviewResponse.ReviewData review : data) {
                        reviewList.add(new ViewReviewModel(
                                "User ID: " + review.getUser_id(),
                                review.getCategory(),
                                review.getReview(),
                                getStarDisplay(review.getStars())
                        ));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ViewReviewPage.this, "No reviews found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Toast.makeText(ViewReviewPage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getStarDisplay(String stars) {
        try {
            int starCount = Integer.parseInt(stars);
            return "★".repeat(starCount) + "☆".repeat(5 - starCount);
        } catch (Exception e) {
            return stars;
        }
    }
}
