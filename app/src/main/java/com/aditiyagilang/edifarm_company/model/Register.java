package com.aditiyagilang.edifarm_company.model;

import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("data")
	private Data data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("massage")
	private String massage;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMassage(String massage){
		this.massage = massage;
	}

	public String getMassage(){
		return massage;
	}
}