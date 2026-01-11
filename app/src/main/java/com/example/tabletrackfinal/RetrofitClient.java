package com.example.tabletrackfinal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // Base URL from API Document Page 1
    private static final String BASE_URL = "http://10.240.72.69/comp2000/coursework/";
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
