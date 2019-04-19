package com.example.pritamsankadiya.jetpackdemo.data.utils;

import com.example.pritamsankadiya.jetpackdemo.data.model.MultiViewResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.example.pritamsankadiya.jetpackdemo.data.utils.Status.ERROR;
import static com.example.pritamsankadiya.jetpackdemo.data.utils.Status.LOADING;
import static com.example.pritamsankadiya.jetpackdemo.data.utils.Status.SUCCESS;

public class ApiResponseImages {

    public final Status status;

    @Nullable
    public final MultiViewResponse data;

    @Nullable
    public final Throwable error;

    private ApiResponseImages(Status status, @Nullable MultiViewResponse data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponseImages loading() {
        return new ApiResponseImages(LOADING, null, null);
    }

    public static ApiResponseImages success(@NonNull MultiViewResponse data) {
        return new ApiResponseImages(SUCCESS, data, null);
    }

    public static ApiResponseImages error(@NonNull Throwable error) {
        return new ApiResponseImages(ERROR, null, error);
    }

}
