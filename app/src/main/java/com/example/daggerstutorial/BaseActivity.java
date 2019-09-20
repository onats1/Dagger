package com.example.daggerstutorial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.daggerstutorial.remotedb.models.User;
import com.example.daggerstutorial.ui.authActivity.AuthActivity;
import com.example.daggerstutorial.ui.authActivity.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    private void subscribeObserver(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            break;
                        }

                        case AUTHENTICATED: {
                            Log.d(TAG, "onChanged: " + userAuthResource.data.getEmail());
                            break;
                        }

                        case NOT_AUTHENTICATED: {
                            navLoginScreen();
                            break;
                        }

                        case ERROR: {
                            Log.e(TAG, "onChanged: " + userAuthResource.message );

                            break;
                        }
                    }
                }
            }
        });
    }
}
