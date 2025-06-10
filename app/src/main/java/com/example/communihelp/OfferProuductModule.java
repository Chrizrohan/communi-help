package com.example.communihelp;

public class OfferProuductModule {
    private String name;
    private String category;
    private String subCategory;
    private float rating;

    public OfferProuductModule(String name, String category, String subCategory, float rating) {
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.rating = rating;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getSubCategory() { return subCategory; }
    public float getRating() { return rating; }
}
