package com.example.projectgistsandroid.data.remote.entities

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

sealed class DataEntities

data class GistPayload(
    @SerializedName("id")
    val id: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerPayload?,
    @SerializedName("files")
    val files: JsonElement?
) : DataEntities()

data class OwnerPayload(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
) : DataEntities()

data class FilePayload(
    @SerializedName("raw_url")
    val rawUrl: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("type")
    val type: String?
)