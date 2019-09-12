package com.example.daggerstutorial.ui.authActivity;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.daggerstutorial.remotedb.auth.AuthApi;
import com.example.daggerstutorial.remotedb.models.User;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";


    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: Started");

        authApi.getData(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user.email);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
