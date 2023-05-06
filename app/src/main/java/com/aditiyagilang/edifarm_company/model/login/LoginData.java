package com.aditiyagilang.edifarm_company.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("id")
    private String id;
    @SerializedName("address")
    private String address;

    @SerializedName("level")
    private String level;

    @SerializedName("phone")
    private String phone;

    @SerializedName("name")
    private String name;

    @SerializedName("photo")
    private String photo;

    @SerializedName("born_date")
    private String bornDate;

    @SerializedName("email")
    private String email;

    @SerializedName("token")
    private String token;

    @SerializedName("username")
    private String username;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "id = '" + id + '\'' +
                        "address = '" + address + '\'' +
                        ",level = '" + level + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",name = '" + name + '\'' +
                        ",photo = '" + photo + '\'' +
                        ",born_date = '" + bornDate + '\'' +
                        ",email = '" + email + '\'' +
                        ",token = '" + token + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}