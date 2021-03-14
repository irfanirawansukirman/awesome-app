package com.irfanirawansukirman.network.di

import android.app.Application
import com.irfanirawansukirman.network.factory.ApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(application: Application): Retrofit = ApiFactory.build(application)
}