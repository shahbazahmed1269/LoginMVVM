package com.example.shahbazahmed.loginmvvmdatabinding.di;

import com.example.shahbazahmed.loginmvvmdatabinding.validators.EmailValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.NameValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PasswordValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PhoneValidator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shahbazahmed on 15/08/17.
 */

@Module
public class ValidatorModule {

    @Provides
    @Singleton
    PhoneValidator providePhoneValidator() {
        return new PhoneValidator("Invalid Phone number");
    }

    @Provides
    @Singleton
    EmailValidator provideEmailValidator() {
        return new EmailValidator("Invalid Email");
    }

    @Provides
    @Singleton
    NameValidator provideNameValidator() {
        return new NameValidator("Name cannot be empty");
    }

    @Provides
    @Singleton
    PasswordValidator providePasswordValidator() {
        return new PasswordValidator("Password should be between 6 to 15 characters");
    }
}
