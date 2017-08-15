package com.example.shahbazahmed.loginmvvmdatabinding.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shahbazahmed.loginmvvmdatabinding.R;
import com.example.shahbazahmed.loginmvvmdatabinding.databinding.ActivityLoginBinding;
import com.example.shahbazahmed.loginmvvmdatabinding.di.DaggerAppComponent;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.LoginViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginViewModel.ViewListener {
    @Inject
    LoginViewModel viewModel;
    private LinearLayout mLlParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_login
        );
        mLlParent = binding.llParent;
        DaggerAppComponent.builder().build().inject(this);
        viewModel.setViewListener(this);
        binding.setViewModel(viewModel);
        MaterialEditText emailEditText = binding.etEmailLogin;
        MaterialEditText passwordEditText = binding.etPasswordLogin;
        emailEditText.setAutoValidate(true);
        passwordEditText.setAutoValidate(true);
        emailEditText.addValidator(viewModel.getEmailValidator());
        passwordEditText.addValidator(viewModel.getPasswordValidator());
        Button btnRegister = findViewById(R.id.btn_create);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        TextView tvPasswordForgot = findViewById(R.id.tv_password_forgot);
        tvPasswordForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        // Handle successful login
        Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
        i.putExtra("email", viewModel.getEmail());
        startActivity(i);
    }

    @Override
    public void onMessage(String message) {
        // Hide soft keyboard
        InputMethodManager imm = (InputMethodManager) this.getSystemService(
                Activity.INPUT_METHOD_SERVICE
        );
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        Snackbar.make(mLlParent, message, Snackbar.LENGTH_LONG).show();

    }
}
