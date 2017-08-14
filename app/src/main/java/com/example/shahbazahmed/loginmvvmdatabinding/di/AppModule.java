package com.example.shahbazahmed.loginmvvmdatabinding.di;

import com.example.shahbazahmed.loginmvvmdatabinding.repositories.RealmUserRepository;
import com.example.shahbazahmed.loginmvvmdatabinding.repositories.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shahbazahmed on 14/08/17.
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    UserRepository provideIssueRepository(RealmUserRepository repository) {
        return repository;
    }
}
