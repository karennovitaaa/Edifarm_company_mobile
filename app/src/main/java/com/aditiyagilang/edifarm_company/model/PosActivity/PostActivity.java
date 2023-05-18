package com.aditiyagilang.edifarm_company.model.PosActivity;

import com.google.gson.annotations.SerializedName;

public class PostActivity {

    @SerializedName("data")
    private PostActivityData postActivityData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public PostActivityData getData() {
        return postActivityData;
    }

    public void setData(PostActivityData postActivityData) {
        this.postActivityData = postActivityData;
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