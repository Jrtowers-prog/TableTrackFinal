package com.example.tabletrackfinal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("students")
    Call<UserResponse> registerUser(@Body User user);

    @GET("students")
    Call<UserResponse> getUser(@Query("username") String username);
}
