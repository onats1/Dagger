package com.example.daggerstutorial.di.appModules;


import com.example.daggerstutorial.di.auth.AuthActivityModule;
import com.example.daggerstutorial.di.auth.AuthScope;
import com.example.daggerstutorial.di.auth.AuthViewModelsModule;
import com.example.daggerstutorial.di.main.MainFragmentBuildersModule;
import com.example.daggerstutorial.di.main.MainModule;
import com.example.daggerstutorial.di.main.MainScope;
import com.example.daggerstutorial.di.main.MainViewModelsModule;
import com.example.daggerstutorial.ui.authActivity.AuthActivity;
import com.example.daggerstutorial.ui.authActivity.AuthViewModel;
import com.example.daggerstutorial.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthActivityModule.class}
    )
    abstract AuthActivity contributAuthActivityInjection();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributesMainActivity();
}
