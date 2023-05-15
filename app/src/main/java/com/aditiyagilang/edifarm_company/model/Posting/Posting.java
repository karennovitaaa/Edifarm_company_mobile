package com.aditiyagilang.edifarm_company.model.Posting;

import com.google.gson.annotations.SerializedName;

public class Posting {

    @SerializedName("data")
    private PostingData postingData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public PostingData getData() {
        return postingData;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}