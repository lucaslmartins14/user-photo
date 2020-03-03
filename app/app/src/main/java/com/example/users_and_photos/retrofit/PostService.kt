package com.example.users_and_photos.retrofit

import com.example.users_and_photos.model.entity.Post
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface PostService {


    @GET("/posts")
    fun getPosts(@Query("userId") userId: String): Observable<List<Post>>
}