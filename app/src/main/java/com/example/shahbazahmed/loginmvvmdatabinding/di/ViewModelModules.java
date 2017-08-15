package com.example.shahbazahmed.loginmvvmdatabinding.di;

import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.ForgotPasswordViewModel;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.LoginViewModel;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.ProfileViewModel;
import com.example.shahbazahmed.loginmvvmdatabinding.viewmodels.RegisterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by shahbazahmed on 15/08/17.
 */

@Module
abstract class ViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract RegisterViewModel provideRegisterViewModel(RegisterViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract LoginViewModel provideLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel.class)
    abstract ForgotPasswordViewModel provideForgotPasswordViewModel(ForgotPasswordViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ProfileViewModel provideProfileViewModel(ProfileViewModel viewModel);
}
