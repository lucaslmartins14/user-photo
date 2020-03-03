package com.example.users_and_photos.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun userService(): UserService? = retrofit.create(UserService::class.java)

    fun photoService(): PhotoService? = retrofit.create(PhotoService::class.java)

    fun postService(): PostService? = retrofit.create(PostService::class.java)
}