package com.example.communihelp.server;

import java.util.List;

public class ReviewResponse {
    private boolean status;
    private String message;
    private List<ReviewData> data;

    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public List<ReviewData> getData() { return data; }

    public static class ReviewData {
        private String reviewid;
        private String ref_id;
        private String user_id;
        private String category;
        private String details;
        private String review;
        private String stars;

        public String getReviewid() { return reviewid; }
        public String getRef_id() { return ref_id; }
        public String getUser_id() { return user_id; }
        public String getCategory() { return category; }
        public String getDetails() { return details; }
        public String getReview() { return review; }
        public String getStars() { return stars; }
    }
}
