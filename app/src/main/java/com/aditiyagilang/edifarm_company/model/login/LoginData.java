package com.aditiyagilang.edifarm_company.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("level")
	private String level;

	@SerializedName("token")
	private String token;

	@SerializedName("username")
	private String username;

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}