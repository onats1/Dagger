package com.example.daggerstutorial.remotedb.auth;

import com.example.daggerstutorial.remotedb.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("/users/{id}")
    Flowable<User> getData(
            @Path("id")int id
    );

}
