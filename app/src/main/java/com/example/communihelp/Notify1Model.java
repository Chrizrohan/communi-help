package com.example.communihelp;

public class Notify1Model {


    private String ref_id,user_id,category,details;

    public Notify1Model(String ref_id, String user_id, String category, String details) {
        this.ref_id = ref_id;
        this.user_id = user_id;
        this.category = category;
        this.details = details;
    }

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


}
