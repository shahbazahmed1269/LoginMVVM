package com.example.shahbazahmed.loginmvvmdatabinding;

import android.app.Application;

import com.example.shahbazahmed.loginmvvmdatabinding.di.AppComponent;
import com.example.shahbazahmed.loginmvvmdatabinding.di.DaggerAppComponent;

import io.realm.Realm;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public class LoginApplication extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().build();
        Realm.init(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}