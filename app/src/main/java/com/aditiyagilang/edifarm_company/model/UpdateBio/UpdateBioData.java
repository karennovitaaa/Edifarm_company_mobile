package com.aditiyagilang.edifarm_company.model.UpdateBio;

import com.google.gson.annotations.SerializedName;

public class UpdateBioData {

	@SerializedName("address")
	private String address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("born_date")
	private String bornDate;

	@SerializedName("username")
	private String username;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setBornDate(String bornDate){
		this.bornDate = bornDate;
	}

	public String getBornDate(){
		return bornDate;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}