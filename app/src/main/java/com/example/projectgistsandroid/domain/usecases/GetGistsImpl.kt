package com.example.projectgistsandroid.domain.usecases

import com.example.projectgistsandroid.data.repositories.interfaces.GistRepository
import com.example.projectgistsandroid.domain.toPresentation
import com.example.projectgistsandroid.domain.usecases.interfaces.GetGists
import com.example.projectgistsandroid.presentation.entities.PresentationEntities
import io.reactivex.Single

class GetGistsImpl(private val repository: GistRepository) : GetGists {
    override fun getGists(page: String): Single<List<PresentationEntities>> = repository.getGist(page).map { item ->
        item.map { it.toPresentation() }
    }
}