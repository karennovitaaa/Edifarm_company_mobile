package com.aditiyagilang.edifarm_company.model.activity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Activity  {

	@SerializedName("data")
	private List<ActivityDataItem> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<ActivityDataItem> data){
		this.data = data;
	}

	public List<ActivityDataItem> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Activity{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}