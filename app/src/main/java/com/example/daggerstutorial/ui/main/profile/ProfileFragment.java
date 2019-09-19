package com.example.daggerstutorial.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.daggerstutorial.R;
import com.example.daggerstutorial.di.viewModels.ViewModelProviderFactory;
import com.example.daggerstutorial.remotedb.models.User;
import com.example.daggerstutorial.ui.authActivity.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private ProfileViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    private TextView email, username, website;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "Profile fragment", Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource!=null){
                    switch (userAuthResource.status){
                        case ERROR:{

                        }
                        case AUTHENTICATED:{
                           setUserDetails(userAuthResource);
                        }
                        case NOT_AUTHENTICATED:{

                        }
                    }
                }
            }
        });
    }

    private void setUserDetails(AuthResource<User> userAuthResource){
        if(userAuthResource.data!= null) {
            email.setText(userAuthResource.data.getEmail());
            username.setText(userAuthResource.data.getUsername());
            website.setText(userAuthResource.data.getWebsite());
        }
    }
}
