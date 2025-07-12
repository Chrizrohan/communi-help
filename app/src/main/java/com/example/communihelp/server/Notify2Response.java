package com.example.communihelp.server;

import com.example.communihelp.Notify2Model;

import java.util.List;

public class Notify2Response {
    private boolean status;
    private String message;
    private List<Notify2Model> data;

    public List<Notify2Model> getData() {
        return data;
    }
}
