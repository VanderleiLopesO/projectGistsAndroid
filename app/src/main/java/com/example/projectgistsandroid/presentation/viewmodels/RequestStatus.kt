package com.example.projectgistsandroid.presentation.viewmodels

import java.io.Serializable

sealed class RequestStatus : Serializable
object None : RequestStatus()
object Loading : RequestStatus()
object Loaded : RequestStatus()
object Error : RequestStatus()