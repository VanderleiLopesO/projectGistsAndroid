package com.example.projectgistsandroid.data.remote.entities

import com.google.gson.annotations.SerializedName

data class OwnerPayload(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)