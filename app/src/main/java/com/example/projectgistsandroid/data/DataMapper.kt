package com.example.projectgistsandroid.data

import com.example.projectgistsandroid.data.remote.entities.FilePayload
import com.example.projectgistsandroid.data.remote.entities.GistPayload
import com.example.projectgistsandroid.domain.entities.FileItem
import com.example.projectgistsandroid.domain.entities.GistItem
import com.example.projectgistsandroid.domain.entities.OwnerItem
import com.google.gson.Gson

fun GistPayload.toDomain(): GistItem {
    val fileEntry = this.files?.asJsonObject?.entrySet()?.firstOrNull()
    val file = Gson().fromJson(fileEntry?.value, FilePayload::class.java)

    return GistItem(
        id ?: "",
        description ?: "",
        OwnerItem(owner?.id ?: 0, owner?.login ?: "", owner?.avatarUrl ?: ""),
        FileItem(file.rawUrl ?: "", file.language ?: "", file.type ?: "")
    )
}
