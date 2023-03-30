package com.aditiyagilang.edifarm_company.model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("name")
	private String name;

	@SerializedName("token")
	private String token;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}