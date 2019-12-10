package com.irfanirawansukirman.data.di

import com.irfanirawansukirman.data.BuildConfig
import com.irfanirawansukirman.data.network.services.MovieApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single { createOkHttpClient(get<HttpLoggingInterceptor>()) }
    single { createApiService<MovieApi>(get(), BuildConfig.MOVIE_URL) }
}

fun createOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .apply {
            if (BuildConfig.DEBUG) {
                for (intercept in interceptors) {
                    addInterceptor(intercept)
                }
            }
        }
        .build()
}

inline fun <reified T> createApiService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()) // converter using gson
        //.addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))n// converter using kotlin serial
        .build()
    return retrofit.create(T::class.java)
}