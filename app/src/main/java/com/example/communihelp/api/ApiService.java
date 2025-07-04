package com.example.communihelp.api;

import com.example.communihelp.server.AddOfferMedicalEmergencyResponse;
import com.example.communihelp.server.AddOfferProductResponse;
import com.example.communihelp.server.AddOfferServiceResponse;
import com.example.communihelp.server.AddRequestMedicalEmergencyResponse;
import com.example.communihelp.server.AddRequestProductResponse;
import com.example.communihelp.server.AddRequestServiceResponse;
import com.example.communihelp.server.ChangePasswordResponce;
import com.example.communihelp.server.EditProfileResponse;
import com.example.communihelp.server.HistoryResponse;
import com.example.communihelp.server.LoginResponse;
import com.example.communihelp.server.ProductResponse;
import com.example.communihelp.server.ProfileResponse;

import com.example.communihelp.server.SignupResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
    @FormUrlEncoded
    @POST("profile.php")
    Call<ProfileResponse> getUserProfile(
            @Field("user_id") String userId
    );



        @Multipart
        @POST("editprofile.php")
        Call<EditProfileResponse> updateProfile(
                @Part("user_id") RequestBody userId,
                @Part("username") RequestBody username,
                @Part("phone_number") RequestBody phoneNumber,
                @Part("email") RequestBody email
        );
    @FormUrlEncoded
    @POST("changepassword.php")
    Call<ChangePasswordResponce> changePassword(
            @Field("user_id") String userId,
            @Field("oldpass") String oldPassword,
            @Field("newpass") String newPassword
    );
    @GET("myhistoryoffer.php")
    Call<HistoryResponse> getOfferHistory(@Query("user_id") String userId);

    @GET("myhistoryrequest.php")
    Call<HistoryResponse> getRequestHistory(@Query("user_id") String userId);
    @FormUrlEncoded
    @POST("addofferproduct.php")
    Call<AddOfferProductResponse> addOfferProduct(
            @Field("user_id") String userId,
            @Field("category") String category,
            @Field("details") String details
    );
    @FormUrlEncoded
    @POST("addoffermedical.php")
    Call<AddOfferMedicalEmergencyResponse> addMedicalEmergencyOffer(
            @Field("user_id") String userId,
            @Field("category") String category,
            @Field("details") String details
    );
    @FormUrlEncoded
    @POST("addofferservice.php")
    Call<AddOfferServiceResponse> addOfferService(
            @Field("user_id") String userId,
            @Field("category") String category,
            @Field("details") String details
    );
    @FormUrlEncoded
    @POST("addrequestproduct.php")
    Call<AddRequestProductResponse> addRequestProduct(
            @Field("user_id") String userId,
            @Field("category") String category,
            @Field("details") String details
    );
    @FormUrlEncoded
    @POST("addrequestmedical.php")
    Call<AddRequestMedicalEmergencyResponse> addMedicalEmergencyRequest(
            @Field("user_id") String userId,
            @Field("category") String category,
            @Field("details") String details
    );
    @FormUrlEncoded
    @POST("addrequestservice.php")
    Call<AddRequestServiceResponse> addServiceRequest(
            @Field("user_id") String userId,
            @Field("category") String category,
            @Field("details") String details
    );

    @GET("productoffer.php")
    Call<ProductResponse> getProductOffers();

    @GET("productrequest.php")
    Call<ProductResponse> getProductRequests();


}
