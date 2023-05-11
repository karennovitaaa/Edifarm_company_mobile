package com.aditiyagilang.edifarm_company.ui;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DashboardModel{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("massage")
	private String massage;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
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