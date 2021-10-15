package com.example.projectgistsandroid.di

import androidx.lifecycle.SavedStateHandle
import com.example.projectgistsandroid.data.remote.DataConstants
import com.example.projectgistsandroid.data.remote.GithubApi
import com.example.projectgistsandroid.data.repositories.GistRepositoryImpl
import com.example.projectgistsandroid.data.repositories.interfaces.GistRepository
import com.example.projectgistsandroid.domain.usecases.GetGistsImpl
import com.example.projectgistsandroid.domain.usecases.interfaces.GetGists
import com.example.projectgistsandroid.presentation.viewmodels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object KoinModule {

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(DataConstants.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val presentationDI = module {
        factory { SavedStateHandle() }

        viewModel { MainViewModel(get(), get(), get()) }
    }

    private val domainDI = module {
        factory<GetGists> {
            GetGistsImpl(get())
        }
    }

    private val dataDI = module {
        factory { retrofit }

        factory<GithubApi> {
            retrofit.create(GithubApi::class.java)
        }

        factory<GistRepository> {
            GistRepositoryImpl(get())
        }
    }

    val appModule = listOf(presentationDI, domainDI, dataDI)

}