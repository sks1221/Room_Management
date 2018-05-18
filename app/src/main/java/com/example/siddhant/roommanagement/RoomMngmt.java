package com.example.siddhant.roommanagement;

import com.example.siddhant.roommanagement.insert_saving.InsertSavingPojo;
import com.example.siddhant.roommanagement.insert_saving.SpinnerPojo;
import com.example.siddhant.roommanagement.login.LoginResponsePojo;
import com.example.siddhant.roommanagement.register.RegisterResponsePojo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RoomMngmt {

    // this for Registration
    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<RegisterResponsePojo> registerresponsepojo(@Body JsonObject jsonObject);


    //this for Login
    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<LoginResponsePojo> loginresponsepojo(@Body JsonObject jsonObject);


    // for Spinner
    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<SpinnerPojo> addSavingpojo(@Body JsonObject jsonObject);


    // for insertSaving
    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<InsertSavingPojo> insertSaving(@Body JsonObject jsonObject);

}
