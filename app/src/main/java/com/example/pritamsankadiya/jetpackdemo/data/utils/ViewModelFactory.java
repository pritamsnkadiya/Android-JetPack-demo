package com.example.pritamsankadiya.jetpackdemo.data.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import com.example.pritamsankadiya.jetpackdemo.data.repository.Repository;
import com.example.pritamsankadiya.jetpackdemo.ui.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
