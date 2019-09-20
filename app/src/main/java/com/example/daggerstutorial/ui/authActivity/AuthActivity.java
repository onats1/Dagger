package com.example.daggerstutorial.ui.authActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.bumptech.glide.RequestManager;
import com.example.daggerstutorial.R;
import com.example.daggerstutorial.di.viewModels.ViewModelProviderFactory;
import com.example.daggerstutorial.remotedb.models.User;
import com.example.daggerstutorial.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    @Inject
    RequestManager glideManager;

    @Inject
    Drawable logo;

    @Inject
    ViewModelProviderFactory providerFactory;

    EditText userId;

    Button loginButton;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.editText);
        loginButton = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(this);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        glideManager.load(logo)
                .into((ImageView) findViewById(R.id.imageView));

        subscribeListeners();

    }

    private void subscribeListeners() {
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }

                        case AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS" + userAuthResource.data.getEmail());
                            openMainActivity();
                            break;
                        }

                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            break;
                        }

                        case ERROR: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: " + userAuthResource.message);
                            break;
                        }
                    }
                }

            }
        });
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == loginButton.getId()) {
            if (!TextUtils.isEmpty(userId.getText().toString())) {
                viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
            }
        }
    }
}
