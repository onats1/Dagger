package com.example.daggerstutorial.ui.authActivity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerstutorial.SessionManager;
import com.example.daggerstutorial.remotedb.auth.AuthApi;
import com.example.daggerstutorial.remotedb.models.User;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){

        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: Started");
        /*authApi.getData(1)
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
                });*/
    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: ");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId){
        return LiveDataReactiveStreams
                .fromPublisher(
                        authApi.getData(userId)
                                .onErrorReturn(new Function<Throwable, User>() {
                                    @Override
                                    public User apply(Throwable throwable) throws Exception {
                                        User errorUser = new User();
                                        errorUser.setUserid(-1);
                                        return errorUser;
                                    }
                                })
                                .map(new Function<User, AuthResource<User>>() {
                                    @Override
                                    public AuthResource<User> apply(User user) throws Exception {
                                        if (user.getUserid() == -1) {
                                            return AuthResource.error("Could not Authenticate.", (User) null);
                                        }
                                        return AuthResource.authenticated(user);
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                );
    }


    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }


}
