package com.example.communihelp.server;


import java.util.List;

public class Notify1Response {
    private boolean status;
    private String message;
    private List<Data> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private String ref_id;
        private String user_id;
        private String category;
        private String details;
        private String phone_number;
        private String username;

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

        public String getPhone_number() {
            return phone_number;
        }

        public String getUsername() {
            return username;
        }
    }
}

