package com.example.shahbazahmed.loginmvvmdatabinding.viewmodels;

import android.databinding.BaseObservable;
import android.util.Log;

import com.example.shahbazahmed.loginmvvmdatabinding.BuildConfig;
import com.example.shahbazahmed.loginmvvmdatabinding.api.ElasticEmailClient;
import com.example.shahbazahmed.loginmvvmdatabinding.entities.User;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.validators.EmailValidator;

import java.security.SecureRandom;

/**
 * Created by shahbazahmed on 15/08/17.
 */

public class ForgotPasswordViewModel extends BaseObservable {
    private String email;
    private boolean resetEnabled;
    private EmailValidator mEmailValidator;
    private ViewListener mListener;

    private UserRepository mUserRepository;

    public ForgotPasswordViewModel(UserRepository userRepository) {
        this.mUserRepository = userRepository;
        email = "";
        resetEnabled = false;
        mEmailValidator = new EmailValidator("Invalid Email");
    }

    public void setViewListener(ViewListener mListener) {
        this.mListener = mListener;
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange();
        setResetEnabled(isInputValid());
    }

    public boolean isResetEnabled() {
        return resetEnabled;
    }

    public void setResetEnabled(boolean resetEnabled) {
        this.resetEnabled = resetEnabled;
    }

    public EmailValidator getEmailValidator() {
        return mEmailValidator;
    }

    private boolean isInputValid() {
        return mEmailValidator.isValid(email, email.length() == 0);
    }

    public void onResetClick() {
        if (isInputValid()) {
            setResetEnabled(false);
            try {
                User user = mUserRepository.fetchByEmail(email);
                if (user != null && user.getEmail().equals(email)) {
                    // User exists in local DB, generate new password
                    final String newPassword = randomString(8);
                    user.setPassword(newPassword);
                    mUserRepository.update(user);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String res = ElasticEmailClient.send(
                                    BuildConfig.ELASTICMAIL_USER,
                                    BuildConfig.ELASTICMAIL_API_KEY,
                                    "support@loginapp.com",
                                    "LoginApp support team",
                                    "Your Login App Password reset",
                                    "Your password is: " + newPassword,
                                    email,
                                    "false"
                            );
                            Log.d("ForgotPasswordViewModel", "Email sent Response: " + res);
                        }
                    }).start();
                    mListener.onEmailSentSuccess();
                } else {
                    // User not found
                    mListener.onMessage("Email not found", "The given email is not registered. Please create an account first.");
                }
            } catch (Exception e) {
                Log.d("LoginViewModel", "Error while saving: " + e.getMessage());
            } finally {
                setResetEnabled(true);
            }
        }
    }

    String randomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public interface ViewListener {

        void onEmailSentSuccess();

        void onMessage(String title, String message);
    }
}
