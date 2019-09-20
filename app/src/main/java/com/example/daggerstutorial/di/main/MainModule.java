package com.example.daggerstutorial.di.main;


import com.example.daggerstutorial.remotedb.main.MainApi;
import com.example.daggerstutorial.ui.main.posts.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static PostRecyclerAdapter providePostRecylerAdapter(){
        return new PostRecyclerAdapter();
    }

    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }


}
