package com.example.projectgistsandroid.domain.usecases.interfaces

import com.example.projectgistsandroid.domain.entities.GistItem
import io.reactivex.Single

interface GetGists {
    fun getGists(page: String): Single<List<GistItem>>
}