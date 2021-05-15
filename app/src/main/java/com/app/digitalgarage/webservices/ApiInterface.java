package com.app.digitalgarage.webservices;/* Created By Ashwini Saraf on 1/13/2021*/


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("login.php")
    Call<JsonObject> login(@Query("mobile_no") String email, @Query("password") String password);

    @GET("admin_login.php")
    Call<JsonObject> adminlogin(@Query("username") String email, @Query("password") String password);

    @GET("register.php")
    Call<JsonObject> register(@Query("fname") String fname,
                              @Query("lname") String lname,
                              @Query("mobile_no") String mobile_no,
                              @Query("car_no") String car_no,
                              @Query("year") String year,
                              @Query("birthday") String birthday,
                              @Query("gender") String gender,
                              @Query("permanent_address") String permanent_address,
                              @Query("present_address") String present_address,
                              @Query("chesis_no") String chesis_no,
                              @Query("password") String password
    );

    @GET("addService.php")
    Call<JsonObject> addService(@Query("user_id") String user_id);

    @GET("addFeedback.php")
    Call<JsonObject> addFeedback(@Query("user_id") String user_id,
                                 @Query("service_id") String service_id,
                                 @Query("rating") String rating,
                                 @Query("comment") String comment);

    @GET("getUserService.php")
    Call<JsonObject> getUserService(@Query("user_id") String user_id);

    @GET("getAllGroups.php")
    Call<JsonObject> getAllGroups();

    @GET("getStudentsGroups.php")
    Call<JsonObject> getStudentsGroups(@Query("id") String id);


    @GET("getComments.php")
    Call<JsonObject> getComments(@Query("id") String id);

    @GET("addComment.php")
    Call<JsonObject> addComment(@Query("group_id") String group_id,
                                @Query("notice_id") String notice_id,
                                @Query("text") String text,
                                @Query("student_id") String student_id);

    @GET("addSubscriber.php")
    Call<JsonObject> addSubscriber(@Query("group_id") String group_id,
                                   @Query("student_id") String student_id);

    @GET("getAllStudents.php")
    Call<JsonObject> getAllStudents();

    @GET("updateProfile.php")
    Call<JsonObject> updateProfile(@Query("id") String id,
                                   @Query("name") String name,
                                   @Query("email") String email,
                                   @Query("mobile_no") String mobile_no,
                                   @Query("branch") String branch,
                                   @Query("bio") String bio);

    @GET("updateFirebaseToken.php")
    Call<JsonObject> updateFirebaseToken(@Query("user_id") String user_id,
                                         @Query("token") String token);


}
