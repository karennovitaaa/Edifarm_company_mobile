package com.aditiyagilang.edifarm_company.model.AddRating;

import com.google.gson.annotations.SerializedName;

public class AddRatingData {

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("rate")
    private String rate;

    @SerializedName("post_activity_id")
    private String postActivityId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPostActivityId() {
        return postActivityId;
    }

    public void setPostActivityId(String postActivityId) {
        this.postActivityId = postActivityId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}