package com.irfanirawansukirman.mykmm

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class ApiClient {
    private val baseUrl = "https://api.themoviedb.org/3/movie/popular?api_key=1b2f29d43bf2e4f3142530bc6929d341"
    private val httpClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    suspend fun getAllMovies() =
        httpClient.get<MovieResponse>(baseUrl)
}