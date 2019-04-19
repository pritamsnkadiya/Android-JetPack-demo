package com.example.pritamsankadiya.jetpackdemo.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.pritamsankadiya.jetpackdemo.data.repository.Repository;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ApiResponse;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ApiResponseImages;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<ApiResponseImages> imageresponseLiveData = new MutableLiveData<>();

    public LoginViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> loginResponse() {
        return responseLiveData;
    }

    public MutableLiveData<ApiResponseImages> imageResponse() {
        return imageresponseLiveData;
    }

    /*
     * method to call normal login api with $(mobileNumber + password)
     * */
    public void hitLoginApi() {

        disposables.add(repository.executeLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    /*
     * method to call normal login api with $(mobileNumber + password)
     * */
    public void hitImagesApi() {
        disposables.add(repository.executeImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> imageresponseLiveData.setValue(ApiResponseImages.loading()))
                .subscribe(
                        result -> imageresponseLiveData.setValue(ApiResponseImages.success(result)),
                        throwable -> imageresponseLiveData.setValue(ApiResponseImages.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
