package com.example.daggerstutorial.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerstutorial.R;
import com.example.daggerstutorial.di.viewModels.ViewModelProviderFactory;
import com.example.daggerstutorial.remotedb.models.Post;
import com.example.daggerstutorial.ui.main.Resource;
import com.example.daggerstutorial.utils.VerticalItemSpacingDecorator;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";
    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    PostRecyclerAdapter recyclerAdapter;

    private RecyclerView recyclerView;
    private PostViewModel postViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        postViewModel = ViewModelProviders.of(this, providerFactory).get(PostViewModel.class);
        subscribeObservers();
        initRecycler();
        Toast.makeText(getActivity(), "Post Fragment", Toast.LENGTH_SHORT).show();
    }

    private void subscribeObservers(){
        postViewModel.observePosts().removeObservers(getViewLifecycleOwner());
        postViewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource.data!= null){
                    switch (listResource.status){
                        case ERROR:{
                            Log.e(TAG, "onChanged: Error..."+ listResource.message);
                            break;
                        }
                        case LOADING:{
                            Log.d(TAG, "onChanged: Loading...");
                            break;
                        }
                        case SUCCESS:{
                            Log.d(TAG, "onChanged: Success...");
                            recyclerAdapter.setPosts(listResource.data);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new VerticalItemSpacingDecorator(15));
        recyclerView.setAdapter(recyclerAdapter);

    }
}
