package com.example.daggerstutorial.di.main;

import com.example.daggerstutorial.ui.main.posts.PostFragment;
import com.example.daggerstutorial.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributesPostFragment();
}
