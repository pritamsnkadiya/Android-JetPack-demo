package com.example.pritamsankadiya.jetpackdemo.data.repository;

import com.example.pritamsankadiya.jetpackdemo.data.model.MultiViewResponse;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ApiCallInterface;
import com.google.gson.JsonElement;

import io.reactivex.Observable;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }
    /*
     * method to call login api
     * */
    public Observable<JsonElement> executeLogin() {
        return apiCallInterface.login();
    }

    /*
     * method to call fetch image api
     * */
    public Observable<MultiViewResponse> executeImages() {
        return apiCallInterface.imagesApi();
    }
}
