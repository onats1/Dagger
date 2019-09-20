package com.example.daggerstutorial.di;

import android.app.Application;

import com.example.daggerstutorial.BaseApplication;
import com.example.daggerstutorial.SessionManager;
import com.example.daggerstutorial.di.appModules.ActivityBuildersModule;
import com.example.daggerstutorial.di.appModules.AppModule;
import com.example.daggerstutorial.di.viewModels.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class
        }
)

public interface AppComponent extends AndroidInjector<BaseApplication> {



    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
