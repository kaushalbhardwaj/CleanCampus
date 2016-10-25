package com.cleancampus.rest;

import com.cleancampus.model.Complaint;
import com.cleancampus.request.ProfileRequest;
import com.cleancampus.response.ComplaintFeedResponse;
import com.cleancampus.response.ComplaintResponse;
import com.cleancampus.model.User;
import com.cleancampus.response.LoginResponse;
import com.cleancampus.response.ProfileResponse;
import com.cleancampus.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by khome on 19/10/16.
 */

public interface ApiInterface {

    @POST("complaint/")
    Call<ComplaintResponse> sendComplaint(@Body Complaint complaint);

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
