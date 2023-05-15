package com.aditiyagilang.edifarm_company.model.ClearSession;

import com.google.gson.annotations.SerializedName;

public class ClearSession {

    @SerializedName("data")
    private ClearSessionData clearSessionData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public ClearSessionData getData() {
        return clearSessionData;
    }

    public void setData(ClearSessionData clearSessionData) {
        this.clearSessionData = clearSessionData;
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