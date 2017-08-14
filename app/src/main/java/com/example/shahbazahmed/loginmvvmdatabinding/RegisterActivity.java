package com.example.shahbazahmed.loginmvvmdatabinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shahbazahmed.loginmvvmdatabinding.databinding.ActivityRegisterBinding;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.RegisterViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_register
        );
        viewModel = new RegisterViewModel();
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
        nameEditText.addValidator(viewModel.getNameValidator());
        passwordEditText.addValidator(viewModel.getPasswordValidator());
    }
}
