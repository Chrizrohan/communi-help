package com.example.communihelp.server;

import java.util.List;

public class ServiceResponse {
    private boolean status;
    private String message;
    private List<ServiceData> data;

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<ServiceData> getData() { return data; }
    public void setData(List<ServiceData> data) { this.data = data; }

    public static class ServiceData {
        private String ref_id;
        private String user_id;
        private String category;
        private String details;

        private String username;
        private String email;
        private String phone_number;

        // Getters
        public String getRef_id() { return ref_id; }
        public String getUser_id() { return user_id; }
        public String getCategory() { return category; }
        public String getDetails() { return details; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public String getPhone_number() { return phone_number; }

        // Setters
        public void setRef_id(String ref_id) { this.ref_id = ref_id; }
        public void setUser_id(String user_id) { this.user_id = user_id; }
        public void setCategory(String category) { this.category = category; }
        public void setDetails(String details) { this.details = details; }
        public void setUsername(String username) { this.username = username; }
        public void setEmail(String email) { this.email = email; }
        public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    }
}
