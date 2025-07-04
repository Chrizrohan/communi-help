package com.example.communihelp.server;

import java.util.List;

public class ProductResponse {
    private boolean status;
    private String message;
    private List<ProductData> data;

    // Getters and Setters
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<ProductData> getData() { return data; }
    public void setData(List<ProductData> data) { this.data = data; }

    public static class ProductData {
        private String ref_id;
        private String user_id;
        private String category;
        private String details;

        // User details from sign_up
        private String username;
        private String email;
        private String phone_number;

        // Getters and Setters
        public String getRef_id() { return ref_id; }
        public void setRef_id(String ref_id) { this.ref_id = ref_id; }

        public String getUser_id() { return user_id; }
        public void setUser_id(String user_id) { this.user_id = user_id; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone_number() { return phone_number; }
        public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    }
}
