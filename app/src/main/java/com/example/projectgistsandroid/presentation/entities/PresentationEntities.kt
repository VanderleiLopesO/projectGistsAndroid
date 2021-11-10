package com.example.projectgistsandroid.presentation.entities

sealed class PresentationEntities

data class ViewGist(
    val id: String,
    val description: String,
    val ownerId: Long,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val rawUrl: String,
    val language: String,
    val type: String
) : PresentationEntities()

object ViewLoading : PresentationEntities()

object ViewError : PresentationEntities()