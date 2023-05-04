package com.aditiyagilang.edifarm_company.model.update;

import com.google.gson.annotations.SerializedName;

public class UpdateData {

	@SerializedName("address")
	private String address;

	@SerializedName("level")
	private String level;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("photo")
	private String photo;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("born_date")
	private String bornDate;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("longitude")
	private String longitude;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBornDate(String bornDate){
		this.bornDate = bornDate;
	}

	public String getBornDate(){
		return bornDate;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return
			"Data{" +
			"address = '" + address + '\'' +
			",level = '" + level + '\'' +
			",latitude = '" + latitude + '\'' +
			",photo = '" + photo + '\'' +
			",created_at = '" + createdAt + '\'' +
			",updated_at = '" + updatedAt + '\'' +
			",phone = '" + phone + '\'' +
			",name = '" + name + '\'' +
			",id = '" + id + '\'' +
			",born_date = '" + bornDate + '\'' +
			",email = '" + email + '\'' +
			",username = '" + username + '\'' +
			",longitude = '" + longitude + '\'' +
			"}";
		}
}