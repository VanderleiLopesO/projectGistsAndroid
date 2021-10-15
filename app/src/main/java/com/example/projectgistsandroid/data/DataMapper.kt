package com.example.projectgistsandroid.data

import com.example.projectgistsandroid.data.remote.entities.GistPayload
import com.example.projectgistsandroid.domain.entities.GistItem
import com.example.projectgistsandroid.domain.entities.OwnerItem

fun GistPayload.toDomain() =
    GistItem(
        id,
        description ?: "",
        OwnerItem(owner?.id ?: 0, owner?.login ?: "", owner?.avatarUrl ?: "")
    )