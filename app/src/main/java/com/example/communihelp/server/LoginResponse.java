package com.example.communihelp.server;

public class LoginResponse {
    private boolean status;
    private String message;
    private User data; // Optional: if your PHP returns user details

    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public User getData() { return data; }

    public class User {
        private int user_id;
        private String name;
        private String email;
        // Add other fields if your PHP returns more


        public int getId() {
            return user_id;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
