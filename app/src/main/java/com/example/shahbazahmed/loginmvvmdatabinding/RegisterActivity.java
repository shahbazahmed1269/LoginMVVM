package com.example.shahbazahmed.loginmvvmdatabinding;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shahbazahmed.loginmvvmdatabinding.databinding.ActivityRegisterBinding;
import com.example.shahbazahmed.loginmvvmdatabinding.di.DaggerAppComponent;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.RegisterViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity implements RegisterViewModel.ViewListener {
    private RegisterViewModel viewModel;
    @Inject
    UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_register
        );
        DaggerAppComponent.builder().build().inject(this);
        viewModel = new RegisterViewModel(userRepository);
        viewModel.setErrorListener(this);
        binding.setViewModel(viewModel);
        MaterialEditText phoneEditText = binding.etPhone;
        MaterialEditText emailEditText = binding.etEmail;
        MaterialEditText nameEditText = binding.etName;
        MaterialEditText passwordEditText = binding.etPassword;
        phoneEditText.setAutoValidate(true);
        emailEditText.setAutoValidate(true);
        nameEditText.setAutoValidate(true);
        passwordEditText.setAutoValidate(true);
        phoneEditText.addValidator(viewModel.getPhoneValidator());
        emailEditText.addValidator(viewModel.getEmailValidator());
        nameEditText.addValidator(viewModel.getmNameValidator());
        passwordEditText.addValidator(viewModel.getPasswordValidator());
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        );
        startActivity(intent);
    }

    @Override
    public void onError(String title, String message) {
        redirectToLoginWithMessage(title, message);
    }

    private void redirectToLoginWithMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(title)
                .setMessage(message)
                .setNeutralButton(
                        "Login Now",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.setFlags(
                                        Intent.FLAG_ACTIVITY_NEW_TASK |
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                                );
                                startActivity(intent);
                            }
                        }
                ).create()
                .show();
    }
}
