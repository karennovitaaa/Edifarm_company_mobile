package com.aditiyagilang.edifarm_company.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

<<<<<<< HEAD
    private static final String BASE_URL = "https://01a5-140-213-44-33.ap.ngrok.io/api/";
=======
    private static final String BASE_URL = "https://2fb9-118-99-83-51.ngrok-free.app/api/";
>>>>>>> d114a539fbca5e3856680880d1f662265022fd33

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
