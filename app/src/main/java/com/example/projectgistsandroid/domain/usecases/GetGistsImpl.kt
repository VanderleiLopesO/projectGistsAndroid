package com.example.projectgistsandroid.domain.usecases

import com.example.projectgistsandroid.data.repositories.interfaces.GistRepository
import com.example.projectgistsandroid.domain.entities.GistItem
import com.example.projectgistsandroid.domain.usecases.interfaces.GetGists
import io.reactivex.Single

class GetGistsImpl(private val repository: GistRepository) : GetGists {
    override fun getGists(page: String): Single<List<GistItem>> = repository.getGist(page)
}