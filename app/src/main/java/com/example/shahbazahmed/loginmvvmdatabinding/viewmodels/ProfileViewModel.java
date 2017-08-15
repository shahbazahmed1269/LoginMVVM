package com.example.shahbazahmed.loginmvvmdatabinding.viewmodels;

import android.databinding.BaseObservable;

import android.util.Log;


import com.example.shahbazahmed.loginmvvmdatabinding.entities.User;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.NameValidator;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.PhoneValidator;
import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by shahbazahmed on 15/08/17.
 */

public class ProfileViewModel extends BaseObservable {
    private String name, phone, email;
    private boolean mUpdateEnabled;
    private ViewListener mListener;

    private PhoneValidator mPhoneValidator;
    private NameValidator mNameValidator;
    private UserRepository mUserRepository;

    public ProfileViewModel(String email, UserRepository userRepository) {
        this.mUserRepository = userRepository;
        this.email = email;
        User user = userRepository.fetchByEmail(email);
        this.name = user.getName();
        this.phone = user.getPhone();
        mUpdateEnabled = false;
        mPhoneValidator = new PhoneValidator("Invalid Phone number");
        mNameValidator = new NameValidator("Name cannot be empty");
    }

    public void setViewListener(ViewListener listener) {
        this.mListener = listener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
        setUpdateEnabled(isInputValid());
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyChange();
        setUpdateEnabled(isInputValid());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUpdateEnabled() {
        return mUpdateEnabled;
    }

    public void setUpdateEnabled(boolean updateEnabled) {
        this.mUpdateEnabled = updateEnabled;
        notifyChange();
    }

    public boolean isInputValid() {
        return mPhoneValidator.isValid(phone, phone.length() == 0) &&
                mNameValidator.isValid(name, name.length() == 0);
    }

    public void onUpdateClick() {
        if (isInputValid()) {
            setUpdateEnabled(false);
            // Update the user in DB
            try {
                User user = mUserRepository.fetchByEmail(email);
                user.setName(name);
                user.setPhone(phone);
                mUserRepository.update(user);
                mListener.onMessage("Profile details updated.");
            } catch (Exception e) {
                Log.d("UpdateViewModel", "Error while updating user: " + e.getMessage());
                mListener.onMessage("Oops some erro occured!");

            } finally {
                setUpdateEnabled(true);
            }
        }
    }

    public METValidator getPhoneValidator() {
        return mPhoneValidator;
    }

    public NameValidator getmNameValidator() {
        return mNameValidator;
    }

    public interface ViewListener {
        void onMessage(String message);
    }
}


