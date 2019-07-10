package com.hfad.notifierapp.retrofit;

import com.hfad.notifierapp.entity.Body;
import com.hfad.notifierapp.entity.Chat;
import com.hfad.notifierapp.entity.Grade;
import com.hfad.notifierapp.entity.Message;
import com.hfad.notifierapp.entity.Schedule;
import com.hfad.notifierapp.entity.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Content-type: application/json")
    @POST("/getUser")
    Call<User> getUser(@retrofit2.http.Body Body msg);

    @Headers("Content-type: application/json")
    @POST("/addUser")
    Call<Body> addUser(@retrofit2.http.Body User user);

    @GET("/userLogin")
    Call<User> login(@Header("Authorization") String authHead);

    @GET("/getGrades")
    Call<ArrayList<Grade>> getGrades(@Header("Authorization") String authHead);

    @GET("/getSchedules")
    Call<ArrayList<Schedule>> getSchedules(@Header("Authorization") String authHead);

    @GET("/getChats")
    Call<ArrayList<Chat>> getChats(@Header("Authorization") String authHead);

    @Headers("Content-type: application/json")
    @POST("/addChat")
    Call<Body> addChat(@Header("Authorization") String authHead, @retrofit2.http.Body Body body);

    @Headers("Content-type: application/json")
    @POST("/getMessages")
    Call<ArrayList<Message>> getMessages(@Header("Authorization") String authHead, @retrofit2.http.Body Body body);

    @Headers("Content-type: application/json")
    @POST("/addMessage")
    Call<Body> addmessage(@Header("Authorization") String authHead, @retrofit2.http.Body Body body);

}
