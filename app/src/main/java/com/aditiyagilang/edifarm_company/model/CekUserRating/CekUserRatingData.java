package com.aditiyagilang.edifarm_company.model.CekUserRating;

import com.google.gson.annotations.SerializedName;

public class CekUserRatingData {

    @SerializedName("has_rated")
    private boolean hasRated;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("post_activity_id")
    private String postActivityId;

    public boolean isHasRated() {
        return hasRated;
    }

    public void setHasRated(boolean hasRated) {
        this.hasRated = hasRated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostActivityId() {
        return postActivityId;
    }

    public void setPostActivityId(String postActivityId) {
        this.postActivityId = postActivityId;
    }
}