package com.example.communihelp.server;

import com.example.communihelp.HistoryModel;

import java.util.List;

public class HistoryResponse {
    private boolean status;
    private String message;
    private List<HistoryModel> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<HistoryModel> getData() {
        return data;
    }
}
