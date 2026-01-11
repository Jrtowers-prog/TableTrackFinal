package com.example.tabletrackfinal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // Create User Endpoint
    @POST("create_user/{student_id}")
    Call<UserResponse> registerUser(
            @Path("student_id") String studentId,
            @Body User user
    );

    // Read User Endpoint
    @GET("read_user/{student_id}/{username}")
    Call<UserResponse> getUser(
            @Path("student_id") String studentId,
            @Path("username") String username
    );
}
