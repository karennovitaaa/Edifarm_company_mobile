package com.aditiyagilang.edifarm_company.model.Like;

import com.google.gson.annotations.SerializedName;

public class LikeData {

    @SerializedName("post_id")
    private String postId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

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