package com.example.communihelp;

public class HistoryModel {

    private String name;
    private String description;

    public HistoryModel(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
