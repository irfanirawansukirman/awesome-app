package com.irfanirawansukirman.mykmm

class MovieUseCase(private val apiClient: ApiClient) {

    suspend fun getAllMovies() = apiClient.getAllMovies()
}