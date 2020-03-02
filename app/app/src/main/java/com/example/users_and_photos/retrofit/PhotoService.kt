package com.example.users_and_photos.retrofit

import com.example.users_and_photos.model.entity.Photo
import retrofit2.http.GET
import rx.Observable

interface PhotoService {


    @GET("/photos")
    fun getPhotos(): Observable<List<Photo>>
}