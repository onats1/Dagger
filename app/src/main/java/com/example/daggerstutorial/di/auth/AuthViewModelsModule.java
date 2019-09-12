package com.example.daggerstutorial.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.daggerstutorial.di.ViewModelKey;
import com.example.daggerstutorial.remotedb.auth.AuthApi;
import com.example.daggerstutorial.ui.authActivity.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import retrofit2.Retrofit;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindViewModel(AuthViewModel authViewModel);

}
