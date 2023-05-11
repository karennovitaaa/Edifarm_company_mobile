package com.aditiyagilang.edifarm_company.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


<<<<<<< HEAD
    private static final String BASE_URL = "https://98be-103-160-182-11.ngrok-free.app";
=======
    private static final String BASE_URL = "https://98be-103-160-182-11.ngrok-free.app/api/";
>>>>>>> 62bb525c16240a4b45c7c455c570de0fbd3c7304

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
