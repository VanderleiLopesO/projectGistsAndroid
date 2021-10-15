package com.example.projectgistsandroid.domain.entities

data class GistItem(
    val id: String,
    val description: String,
    val owner: OwnerItem
)