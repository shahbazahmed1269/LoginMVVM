package com.example.shahbazahmed.loginmvvmdatabinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shahbazahmed.loginmvvmdatabinding.databinding.ActivityLoginBinding;
import com.example.shahbazahmed.loginmvvmdatabinding.di.DaggerAppComponent;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.LoginViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginViewModel.ViewListener {
    private LoginViewModel viewModel;
    @Inject
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_login
        );
        DaggerAppComponent.builder().build().inject(this);
        viewModel = new LoginViewModel(userRepository);
        viewModel.setErrorListener(this);
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
    }

    @Override
    public void onMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(title).setMessage(message).create().show();
    }
}
