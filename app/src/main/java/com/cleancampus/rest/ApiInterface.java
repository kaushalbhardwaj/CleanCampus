package com.cleancampus.rest;

import com.cleancampus.model.Complaint;
import com.cleancampus.request.ProfileRequest;
import com.cleancampus.response.ComplaintFeedResponse;
import com.cleancampus.response.ComplaintResponse;
import com.cleancampus.model.User;
import com.cleancampus.response.LoginResponse;
import com.cleancampus.response.ProfileResponse;
import com.cleancampus.response.RegisterResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by khome on 19/10/16.
 */

public interface ApiInterface {

    @Multipart
    @POST("complaint/")
    Call<ComplaintResponse> sendComplaint(@PartMap() Map<String, RequestBody> partMap,
                                          @Part MultipartBody.Part file);

    @POST("signup/")
    Call<RegisterResponse> createUser(@Body User user);

    @POST("profile/")
    Call<ProfileResponse> getProfile(@Body ProfileRequest pr);

    @GET("getcomplaint")
    Call<ComplaintFeedResponse> getComplaints();

    @FormUrlEncoded
    @POST("login/")
    Call<LoginResponse> signin(@Field("username") String username, @Field("password") String password);

}
