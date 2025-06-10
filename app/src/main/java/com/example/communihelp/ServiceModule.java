package com.example.communihelp;

public class ServiceModule {
    private String name;
    private String description;
    private float rating;

    public ServiceModule(String name, String description, float rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }
}


