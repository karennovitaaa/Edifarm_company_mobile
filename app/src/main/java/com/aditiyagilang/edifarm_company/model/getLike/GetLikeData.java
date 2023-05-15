package com.aditiyagilang.edifarm_company.model.getLike;

import com.google.gson.annotations.SerializedName;

public class GetLikeData {

    @SerializedName("post_id")
    private int postId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("created_at")
    private String createdAt;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}