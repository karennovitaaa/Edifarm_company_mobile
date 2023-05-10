package com.aditiyagilang.edifarm_company.model.dashboard_model;

import com.google.gson.annotations.SerializedName;

public class DashboardDataItem {

    @SerializedName("image")
    private String image;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private int userId;

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

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostLatitude() {
        return postLatitude;
    }

    public void setPostLatitude(String postLatitude) {
        this.postLatitude = postLatitude;
    }

    public String getPostLongitude() {
        return postLongitude;
    }

    public void setPostLongitude(String postLongitude) {
        this.postLongitude = postLongitude;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
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