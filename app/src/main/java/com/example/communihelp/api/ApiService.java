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
import com.example.communihelp.server.MedicalEmergencyResponse;
import com.example.communihelp.server.Notify1ApiResponse;
import com.example.communihelp.server.Notify1Response;
import com.example.communihelp.server.Notify2ApiResponse;
import com.example.communihelp.server.Notify2Response;
import com.example.communihelp.server.ProductResponse;
import com.example.communihelp.server.ProfileResponse;

import com.example.communihelp.server.ReviewResponse;
import com.example.communihelp.server.ServiceResponse;
import com.example.communihelp.server.SignupResponse;
import com.example.communihelp.server.SimpleResponse;

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
    @GET("medicaloffer.php")
    Call<MedicalEmergencyResponse> getMedicalOffers();

    @GET("medicalrequest.php")
    Call<MedicalEmergencyResponse> getMedicalRequests();
    @GET("serviceoffer.php")
    Call<ServiceResponse> getServiceOffers();

    @GET("servicerequest.php")
    Call<ServiceResponse> getServiceRequests();

    @FormUrlEncoded
    @POST("acceptproductoff.php")
    Call<SimpleResponse> acceptProductOffer(
            @Field("id") String refId,
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST("acceptproductreq.php")
    Call<SimpleResponse> acceptProductRequest(
            @Field("id") String refId,
            @Field("user_id") String userId
    );
    @FormUrlEncoded
    @POST("ignoreoff.php")
    Call<SimpleResponse> ignoreProductOffer(@Field("id") String refId);

    @FormUrlEncoded
    @POST("ignorereq.php")
    Call<SimpleResponse> ignoreProductRequest(@Field("id") String refId);
    @GET("viewreview.php")
    Call<ReviewResponse> getReviews(@Query("user_id") String userId);
    @FormUrlEncoded
    @POST("acceptmedicaloff.php")
    Call<SimpleResponse> acceptMedicalOffer(
            @Field("id") String id,
            @Field("user_id") String userId
    );

    @FormUrlEncoded
    @POST("acceptmedicalreq.php")
    Call<SimpleResponse> acceptMedicalRequest(
            @Field("id") String id,
            @Field("user_id") String userId
    );
    @FormUrlEncoded
    @POST("ignoreoff.php")
    Call<SimpleResponse> ignoreMedicalOffer(@Field("id") String ref_id);

    @FormUrlEncoded
    @POST("ignorereq.php")
    Call<SimpleResponse> ignoreMedicalRequest(@Field("id") String ref_id);
    @FormUrlEncoded
    @POST("acceptserviceoff.php")
    Call<SimpleResponse> acceptServiceOffer(@Field("id") String ref_id, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("acceptservicereq.php")
    Call<SimpleResponse> acceptServiceRequest(@Field("id") String ref_id, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("ignoreoff.php")
    Call<SimpleResponse> ignoreServiceOffer(@Field("id") String ref_id);

    @FormUrlEncoded
    @POST("ignorereq.php")
    Call<SimpleResponse> ignoreServiceRequest(@Field("id") String ref_id);

    @GET("notify1.php")
    Call<Notify1ApiResponse> getNotify1(@Query("user_id") String userId);

    @GET("notify2.php")
    Call<Notify2ApiResponse> getNotify2(@Query("user_id") String userId);
    @FormUrlEncoded
    @POST("yes.php")
    Call<SimpleResponse> sendYesResponse(
            @Field("ref_id") String refId
    );
}


