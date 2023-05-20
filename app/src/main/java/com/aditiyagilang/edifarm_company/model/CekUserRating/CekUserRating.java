package com.aditiyagilang.edifarm_company.model.CekUserRating;

import com.google.gson.annotations.SerializedName;

public class CekUserRating {

    @SerializedName("data")
    private CekUserRatingData cekUserRatingData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public CekUserRatingData getData() {
        return cekUserRatingData;
    }

    public void setData(CekUserRatingData cekUserRatingData) {
        this.cekUserRatingData = cekUserRatingData;
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