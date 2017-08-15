package com.example.shahbazahmed.loginmvvmdatabinding;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.shahbazahmed.loginmvvmdatabinding.databinding.ActivityForgotPasswordBinding;
import com.example.shahbazahmed.loginmvvmdatabinding.di.DaggerAppComponent;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.ForgotPasswordViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

public class ForgotPasswordActivity extends AppCompatActivity
        implements ForgotPasswordViewModel.ViewListener {

    private static final int EMAIL_RESULT_CODE = 1;
    private ForgotPasswordViewModel viewModel;
    @Inject
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityForgotPasswordBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_forgot_password
        );
        DaggerAppComponent.builder().build().inject(this);
        viewModel = new ForgotPasswordViewModel(userRepository);
        viewModel.setViewListener(this);
        binding.setViewModel(viewModel);
        MaterialEditText emailEditText = binding.etEmailForgot;
        emailEditText.setAutoValidate(true);
        emailEditText.addValidator(viewModel.getEmailValidator());
    }


    @Override
    public void onEmailSentSuccess() {
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        );
        startActivity(intent);
    }

    @Override
    public void onMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(title).setMessage(message).create().show();
    }

}
