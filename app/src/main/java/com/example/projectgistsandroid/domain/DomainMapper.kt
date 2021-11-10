package com.example.projectgistsandroid.domain

import com.example.projectgistsandroid.domain.entities.GistItem
import com.example.projectgistsandroid.presentation.entities.ViewGist

fun GistItem.toPresentation() =
    ViewGist(id, description, owner.id, owner.login, owner.avatarUrl, files.rawUrl, files.language, files.type)
