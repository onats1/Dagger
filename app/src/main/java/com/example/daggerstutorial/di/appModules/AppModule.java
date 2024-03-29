package com.example.daggerstutorial.di.appModules;



import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerstutorial.R;
import com.example.daggerstutorial.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.daggerstutorial.utils.Constants.BASE_URL;


@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions.placeholderOf(R.drawable.white_bg)
                .error(R.drawable.white_bg);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

    @Singleton
    @Provides
    static SessionManager provideSessionManager(){
        return new SessionManager();
    }
}
