package com.example.shahbazahmed.loginmvvmdatabinding.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import android.util.Log;


import com.example.shahbazahmed.loginmvvmdatabinding.BR;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.EmailValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.NameValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PasswordValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PhoneValidator;
import com.rengwuxian.materialedittext.validation.METValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public class RegisterViewModel extends BaseObservable {
    private String name;
    private String phone;
    private String email;
    private String password;

    private PhoneValidator phoneValidator;
    private EmailValidator emailValidator;
    private NameValidator nameValidator;
    private PasswordValidator passwordValidator;
    private boolean registerEnabled;

    public RegisterViewModel() {
        name = "";
        phone = "";
        email = "";
        password = "";
        registerEnabled = false;
        phoneValidator = new PhoneValidator("Invalid Phone number");
        emailValidator = new EmailValidator("Invalid Email");
        nameValidator = new NameValidator("Name cannot be empty");
        passwordValidator = new PasswordValidator("Password should be between 6 to 15 characters");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
        setRegisterEnabled(isInputValid());
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyChange();
        setRegisterEnabled(isInputValid());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange();
        setRegisterEnabled(isInputValid());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
        setRegisterEnabled(isInputValid());
    }

    public boolean isRegisterEnabled() {
        return registerEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
        notifyChange();
    }

    public boolean isInputValid() {
        return phoneValidator.isValid(phone, phone.length() == 0) &&
                emailValidator.isValid(email, email.length() == 0) &&
                nameValidator.isValid(name, name.length() == 0) &&
                passwordValidator.isValid(password, password.length() == 0);
    }

    public void onRegisterClick() {
        if (isInputValid()) {
            Log.d("RegisterViewModel", "Saving user in DB");
            // Save the user in DB
        }
    }

    public METValidator getPhoneValidator() {
        return phoneValidator;
    }

    public METValidator getEmailValidator() {
        return emailValidator;
    }

    public NameValidator getNameValidator() {
        return nameValidator;
    }

    public PasswordValidator getPasswordValidator() {
        return passwordValidator;
    }
}


