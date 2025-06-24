package com.example.communihelp;

public class ViewReviewModel {
    private String name;
    private String category;
    private String details;
    private String stars; // e.g., "★★★★★" or "★★★☆☆"

    public ViewReviewModel(String name, String category, String details, String stars) {
        this.name = name;
        this.category = category;
        this.details = details;
        this.stars = stars;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDetails() { return details; }
    public String getStars() { return stars; }
}
