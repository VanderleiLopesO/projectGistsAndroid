package com.example.projectgistsandroid.data.remote.entities

import com.google.gson.annotations.SerializedName

data class GistPayload(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerPayload?
)