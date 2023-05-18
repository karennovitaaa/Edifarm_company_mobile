package com.aditiyagilang.edifarm_company.model.dashboard_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardModel {

    @SerializedName("data")
    private List<DashboardDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<DashboardDataItem> getData() {
        return data;
    }

    public void setData(List<DashboardDataItem> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}