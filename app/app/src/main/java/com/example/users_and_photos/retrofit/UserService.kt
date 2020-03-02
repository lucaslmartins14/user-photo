package com.example.users_and_photos.retrofit

import androidx.lifecycle.LiveData
import com.example.users_and_photos.model.entity.User
import retrofit2.http.GET
import rx.Observable

interface UserService {


    @GET("/users")
    fun getUsers(): Observable<List<User>>
}