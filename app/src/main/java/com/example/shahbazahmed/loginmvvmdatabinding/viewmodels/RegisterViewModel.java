package com.example.shahbazahmed.loginmvvmdatabinding.viewmodels;

import android.databinding.BaseObservable;

import android.util.Log;


import com.example.shahbazahmed.loginmvvmdatabinding.entities.User;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserAlreadyExistsException;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.EmailValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.NameValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PasswordValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PhoneValidator;
import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public class RegisterViewModel extends BaseObservable {
    private String name;
    private String phone;
    private String email;
    private String password;

    private PhoneValidator mPhoneValidator;
    private EmailValidator mEmailValidator;
    private NameValidator mNameValidator;
    private PasswordValidator mPasswordValidator;
    private boolean mRegisterEnabled;

    private UserRepository mUserRepository;
    private ErrorListener mListener;

    public RegisterViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
        name = "";
        phone = "";
        email = "";
        password = "";
        mRegisterEnabled = false;
        mPhoneValidator = new PhoneValidator("Invalid Phone number");
        mEmailValidator = new EmailValidator("Invalid Email");
        mNameValidator = new NameValidator("Name cannot be empty");
        mPasswordValidator = new PasswordValidator("Password should be between 6 to 15 characters");
    }

    public void setErrorListener(ErrorListener listener) {
        this.mListener = listener;
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
        return mRegisterEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.mRegisterEnabled = registerEnabled;
        notifyChange();
    }

    public boolean isInputValid() {
        return mPhoneValidator.isValid(phone, phone.length() == 0) &&
                mEmailValidator.isValid(email, email.length() == 0) &&
                mNameValidator.isValid(name, name.length() == 0) &&
                mPasswordValidator.isValid(password, password.length() == 0);
    }

    public void onRegisterClick() {
        if (isInputValid()) {
            setRegisterEnabled(false);
            // Save the user in DB
            try {
                mUserRepository.save(new User(email, name, phone, password));
            } catch (UserAlreadyExistsException e) {
                Log.d("RegisterViewModel", "Error while saving: " + e.getMessage());
                mListener.onError("User Already Exists", "User with given email already exists.");
            } finally {
                setRegisterEnabled(true);
            }
        }
    }

    public METValidator getPhoneValidator() {
        return mPhoneValidator;
    }

    public METValidator getEmailValidator() {
        return mEmailValidator;
    }

    public NameValidator getmNameValidator() {
        return mNameValidator;
    }

    public PasswordValidator getPasswordValidator() {
        return mPasswordValidator;
    }

    public interface ErrorListener {
        void onError(String header, String message);
    }
}


