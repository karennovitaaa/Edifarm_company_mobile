package com.aditiyagilang.edifarm_company.api;

import com.aditiyagilang.edifarm_company.Activity.UpdateActivity;
import com.aditiyagilang.edifarm_company.model.UpActivity.UpActivity;
import com.aditiyagilang.edifarm_company.model.UpdateBio.UpdateBio;
import com.aditiyagilang.edifarm_company.model.activity.Activity;
import com.aditiyagilang.edifarm_company.model.addActivity.AddActivity;
import com.aditiyagilang.edifarm_company.model.login.Login;
import com.aditiyagilang.edifarm_company.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<Login> loginresponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<Register> registerresponse(
            @Field("username") String username,
            @Field("name") String name,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("born_date") String born_date,
            @Field("email") String email,
            @Field("confirm_password") String confirm_password
    );

    @FormUrlEncoded
    @POST("update")
    Call<UpdateBio> updateResponse(
            @Field("id") String id,
            @Field("username") String username,
            @Field("name") String name,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("born_date") String born_date,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("getact")
    Call<Activity> actResponse(
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("filterActivity")
    Call<Activity> actFilterResponse(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("updateStatus")
    Call<UpActivity> UpactResponse(
            @Field("id") String id,
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("addActivity")
    Call<AddActivity> CreatActResponse(
            @Field("activity_name") String activity_name,
            @Field("status") String status,
            @Field("start") String start,
            @Field("end") String end,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("updateActivitu")
    Call<UpdateActivity> UpdateActResponse(
            @Field("id") String id,
            @Field("activity_name") String activity_name,
            @Field("status") String status,
            @Field("start") String start,
            @Field("end") String end,
            @Field("user_id") String user_id
    );
}
