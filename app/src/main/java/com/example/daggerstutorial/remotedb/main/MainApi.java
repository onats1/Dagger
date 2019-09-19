package com.example.daggerstutorial.remotedb.main;

import com.example.daggerstutorial.remotedb.models.Post;
import com.example.daggerstutorial.remotedb.models.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );
}
