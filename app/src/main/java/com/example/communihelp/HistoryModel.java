package com.example.communihelp;

public class HistoryModel {
    private String ref_id;
    private String user_id;
    private String category;
    private String details;

    public String getRef_id() {
        return ref_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCategory() {
        return category;
    }

    public String getDetails() {
        return details;
    }

    public HistoryModel(String ref_id, String user_id, String category, String details) {
        this.ref_id = ref_id;
        this.user_id = user_id;
        this.category = category;
        this.details = details;
    }

}
