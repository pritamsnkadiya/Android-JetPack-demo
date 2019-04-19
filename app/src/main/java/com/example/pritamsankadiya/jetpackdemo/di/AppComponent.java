package com.example.pritamsankadiya.jetpackdemo.di;


import com.example.pritamsankadiya.jetpackdemo.ui.ui.LoginActivity;
import com.example.pritamsankadiya.jetpackdemo.ui.ui.ShowImages;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {


    void doInjection(LoginActivity loginActivity);
    void doInjection(ShowImages showImages);
}
