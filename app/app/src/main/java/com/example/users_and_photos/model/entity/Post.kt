package com.example.users_and_photos.model.entity

import com.google.gson.annotations.Expose

data class Post (
    @Expose
    val title: String,
    @Expose
    val body: String
)