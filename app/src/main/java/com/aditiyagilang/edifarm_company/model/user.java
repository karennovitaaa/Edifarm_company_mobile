package com.aditiyagilang.edifarm_company.model;

public class user {
    private String username;
    private String nama;
    private String photo;
    private String address;
    private String phone;
    private String born_date;
    private double latitude;
    private double longitude;
    private String email;
    private String password;
    private int level;

    public void User(String username, String nama, String photo, String address, String phone, String born_date, double latitude, double longitude, String email, String password, int level) {
        this.username = username;
        this.nama = nama;
        this.photo = photo;
        this.address = address;
        this.phone = phone;
        this.born_date = born_date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;
        this.password = password;
        this.level = level;


    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBornDate() {
        return born_date;
    }

    public void setBornDate(String born_date) {
        this.born_date = born_date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
