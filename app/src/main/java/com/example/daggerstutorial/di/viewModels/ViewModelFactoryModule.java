package com.example.daggerstutorial.di.viewModels;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerstutorial.di.viewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

}
