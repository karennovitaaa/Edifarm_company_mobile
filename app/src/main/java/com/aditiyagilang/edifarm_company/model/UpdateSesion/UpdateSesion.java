package com.aditiyagilang.edifarm_company.model.UpdateSesion;

import com.google.gson.annotations.SerializedName;

public class UpdateSesion {

    @SerializedName("data")
    private UpdateSesionData updateSesionData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpdateSesionData getData() {
        return updateSesionData;
    }

    public void setData(UpdateSesionData updateSesionData) {
        this.updateSesionData = updateSesionData;
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