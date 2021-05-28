package com.irfanirawansukirman.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.irfanirawansukirman.core.util.coroutine.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider = CoroutineContextProvider()

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences("kita_movie_cache", Context.MODE_PRIVATE)
}