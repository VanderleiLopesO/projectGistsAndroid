package com.example.projectgistsandroid.data.repositories.interfaces

import com.example.projectgistsandroid.domain.entities.GistItem
import io.reactivex.Single

interface GistRepository {
    fun getGist(page: String): Single<List<GistItem>>
}