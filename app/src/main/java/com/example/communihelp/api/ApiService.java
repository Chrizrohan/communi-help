package com.example.communihelp.api;

import com.example.communihelp.server.LoginResponse;
import com.example.communihelp.server.SignupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("signup.php")
    Call<SignupResponse> signup(@Field("username") String username,
                                @Field("email") String email,
                                @Field("phone_number") String phoneNo,
                                @Field("pass") String pass);
    @FormUrlEncoded
    @POST("login.php") // Replace with your actual PHP filename
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("pass") String password);


}
