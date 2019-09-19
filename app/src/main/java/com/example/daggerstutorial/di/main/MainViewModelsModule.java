package com.example.daggerstutorial.di.main;

import androidx.lifecycle.ViewModel;

import com.example.daggerstutorial.di.ViewModelKey;
import com.example.daggerstutorial.ui.main.posts.PostViewModel;
import com.example.daggerstutorial.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindPostViewModel(PostViewModel postViewModel);

}
