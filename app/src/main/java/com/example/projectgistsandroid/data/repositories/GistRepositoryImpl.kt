package com.example.projectgistsandroid.data.repositories

import com.example.projectgistsandroid.data.remote.GithubApi
import com.example.projectgistsandroid.data.repositories.interfaces.GistRepository
import com.example.projectgistsandroid.data.toDomain

class GistRepositoryImpl(private val api: GithubApi) : GistRepository {

    override fun getGist(page: String) =
        api.getGists(page).map { item ->
            item.map { it.toDomain() }
        }

}