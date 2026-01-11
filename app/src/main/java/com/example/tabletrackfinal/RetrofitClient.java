package com.example.tabletrackfinal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // This is the University API URL for this module
    private static final String BASE_URL = "https://web.socem.plymouth.ac.uk/COMP2000/api/";
    private static Retrofit retrofit = null;

    public static ApiService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
