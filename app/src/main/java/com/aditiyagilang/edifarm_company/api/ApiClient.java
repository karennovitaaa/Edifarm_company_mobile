package com.aditiyagilang.edifarm_company.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
<<<<<<< HEAD
    private static final String BASE_URL = "https://01a5-140-213-44-33.ap.ngrok.io/api/";
=======
    private static final String BASE_URL = "https://4e25-114-125-109-214.ngrok-free.app/api/";
>>>>>>> 2c1dda71483edfa9d4a8e07850cf52adfaefbeeb
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
