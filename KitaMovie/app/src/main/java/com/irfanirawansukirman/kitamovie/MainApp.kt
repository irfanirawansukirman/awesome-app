package com.irfanirawansukirman.kitamovie

import android.app.Application
import com.facebook.stetho.Stetho
import com.irfanirawansukirman.movie.di.DaggerMovieAppComponent
import com.irfanirawansukirman.movie.di.MovieAppComponent
import com.irfanirawansukirman.movie.di.MovieComponentProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainApp : Application(), MovieComponentProvider {

    override fun getMovieComponent(): MovieAppComponent {
        return DaggerMovieAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}