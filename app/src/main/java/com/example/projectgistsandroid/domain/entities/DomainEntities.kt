package com.example.projectgistsandroid.domain.entities

sealed class DomainEntities

data class GistItem(
    val id: String,
    val description: String,
    val owner: OwnerItem,
    val files: FileItem
) : DomainEntities()

data class OwnerItem(
    val id: Long,
    val login: String,
    val avatarUrl: String
) : DomainEntities()

data class FileItem(
    val rawUrl: String,
    val language: String,
    val type: String
) : DomainEntities()