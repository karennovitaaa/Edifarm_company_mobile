package com.aditiyagilang.edifarm_company.model.GetPostUser;

import com.google.gson.annotations.SerializedName;

public class GetPostUserDataItem {

    @SerializedName("image")
    private String image;

    @SerializedName("address")
    private String address;
    @SerializedName("fcm_token")
    private Object fcmToken;

    @SerializedName("post_latitude")
    private String postLatitude;

    @SerializedName("post_longitude")
    private String postLongitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("caption")
    private String caption;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("photo")
    private String photo;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("longitude")
    private String longitude;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(Object fcmToken) {
        this.fcmToken = fcmToken;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}