package com.aditiyagilang.edifarm_company.api;

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
            @Field("password") String password,
            @Field("confirm_password") String confirm_password,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("address") String address,

            String email, String confirmPassword);
}
