package com.example.users_and_photos.model.entity

import com.example.users_and_photos.model.entity.Address
import com.google.gson.annotations.Expose

data class User (
    val id: Long,
    @Expose
    val name: String,
    @Expose
    val email: String,
    @Expose
    val address: Address,
    @Expose
    val company: Company
)