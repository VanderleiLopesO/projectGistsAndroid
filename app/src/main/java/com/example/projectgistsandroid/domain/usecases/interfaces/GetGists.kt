package com.example.projectgistsandroid.domain.usecases.interfaces

import com.example.projectgistsandroid.presentation.entities.PresentationEntities
import io.reactivex.Single

interface GetGists {
    fun getGists(page: String): Single<List<PresentationEntities>>
}