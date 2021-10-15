package com.example.projectgistsandroid.data.remote

import com.example.projectgistsandroid.data.remote.entities.GistPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/gists/public")
    fun getGists(
        @Query("page") page: String,
    ): Single<List<GistPayload>>

}