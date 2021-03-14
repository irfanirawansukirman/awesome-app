package com.irfanirawansukirman.movie.di

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface MovieComponentProvider {

    fun getMovieComponent(): MovieAppComponent
}