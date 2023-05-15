package com.aditiyagilang.edifarm_company.model.Posting;

import com.google.gson.annotations.SerializedName;

public class PostingData {

    @SerializedName("image")
    private String image;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("post_latitude")
    private String postLatitude;

    @SerializedName("post_longitude")
    private String postLongitude;

    @SerializedName("caption")
    private String caption;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    public String getImage() {
        return image;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostLatitude() {
        return postLatitude;
    }

    public String getPostLongitude() {
        return postLongitude;
    }

    public String getCaption() {
        return caption;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}