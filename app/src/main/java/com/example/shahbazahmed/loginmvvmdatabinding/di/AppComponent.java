package com.example.shahbazahmed.loginmvvmdatabinding.di;

import com.example.shahbazahmed.loginmvvmdatabinding.activities.ForgotPasswordActivity;
import com.example.shahbazahmed.loginmvvmdatabinding.activities.LoginActivity;
import com.example.shahbazahmed.loginmvvmdatabinding.activities.ProfileActivity;
import com.example.shahbazahmed.loginmvvmdatabinding.activities.RegisterActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shahbazahmed on 14/08/17.
 */

@Singleton
@Component(modules = {AppModule.class, ViewModelModules.class, ValidatorModule.class})
public interface AppComponent {

    void inject(RegisterActivity registerActivity);
    void inject(LoginActivity loginActivity);
    void inject(ForgotPasswordActivity forgotPasswordActivity);
    void inject(ProfileActivity profileActivity);

}