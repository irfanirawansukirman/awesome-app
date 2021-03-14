package com.irfanirawansukirman.network.di

import android.app.Application
import androidx.room.Room
import com.irfanirawansukirman.network.dao.MovieDao
import com.irfanirawansukirman.network.factory.CacheFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheFactory(application: Application): CacheFactory = Room
        .databaseBuilder(application, CacheFactory::class.java, "db_kita_movie")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMovieDao(cacheFactory: CacheFactory): MovieDao = cacheFactory.movieDao()
}