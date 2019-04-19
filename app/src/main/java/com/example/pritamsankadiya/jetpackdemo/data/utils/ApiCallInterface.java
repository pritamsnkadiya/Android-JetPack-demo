package com.example.pritamsankadiya.jetpackdemo.data.utils;

import com.example.pritamsankadiya.jetpackdemo.data.model.MultiViewResponse;
import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiCallInterface {
/*
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    Observable<JsonElement> login(@Field("mobile") String mobileNumber, @Field("password") String password);*/

    @Headers({"Accept: application/json"})
    @GET(Urls.LOGIN)
    Observable<JsonElement> login();


    @Headers({"Accept: application/json"})
    @GET(Urls.IMAGES)
    Observable<MultiViewResponse> imagesApi();
}
