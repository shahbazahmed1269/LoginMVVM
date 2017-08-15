package com.example.shahbazahmed.loginmvvmdatabinding.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.shahbazahmed.loginmvvmdatabinding.R;
import com.example.shahbazahmed.loginmvvmdatabinding.databinding.ActivityProfileBinding;
import com.example.shahbazahmed.loginmvvmdatabinding.di.DaggerAppComponent;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.ProfileViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

public class ProfileActivity extends AppCompatActivity implements ProfileViewModel.ViewListener {
    @Inject
    ProfileViewModel viewModel;
    private LinearLayout mLlParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_profile
        );
        String email = getIntent().getStringExtra("email");
        DaggerAppComponent.builder().build().inject(this);
        viewModel.setEmail(email);
        viewModel.setViewListener(this);
        binding.setViewModel(viewModel);
        MaterialEditText phoneEditText = binding.etPhoneUpdate;
        MaterialEditText emailEditText = binding.etEmailUpdate;
        MaterialEditText nameEditText = binding.etNameUpdate;
        mLlParent = binding.llUpdateParent;
        phoneEditText.setAutoValidate(true);
        emailEditText.setAutoValidate(true);
        nameEditText.setAutoValidate(true);
        phoneEditText.addValidator(viewModel.getPhoneValidator());
        nameEditText.addValidator(viewModel.getmNameValidator());
    }

    @Override
    public void onMessage(String message) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(
                Activity.INPUT_METHOD_SERVICE
        );
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        Snackbar.make(mLlParent, message, Snackbar.LENGTH_LONG).show();
    }
}
