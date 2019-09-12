package com.example.daggerstutorial.di.appModules;


import com.example.daggerstutorial.di.auth.AuthActivityModule;
import com.example.daggerstutorial.di.auth.AuthViewModelsModule;
import com.example.daggerstutorial.ui.authActivity.AuthActivity;
import com.example.daggerstutorial.ui.authActivity.AuthViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthActivityModule.class}
    )
    abstract AuthActivity contributAuthActivityInjection();

}
