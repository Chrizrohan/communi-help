package com.example.communihelp;

public class Notify2Model {
    private String name;
    private String details;
    private String address;

    public Notify2Model(String name, String details, String address) {
        this.name = name;
        this.details = details;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getAddress() {
        return address;
    }
}
