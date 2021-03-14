package com.irfanirawansukirman.movie.presentation.moviesfavorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfanirawansukirman.core.base.BaseVM
import com.irfanirawansukirman.core.ui.UIState
import com.irfanirawansukirman.core.util.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.movie.data.mapper.MoviesUI
import com.irfanirawansukirman.movie.data.mapper.toUI
import com.irfanirawansukirman.movie.domain.MovieUseCaseImpl
import javax.inject.Inject

class MoviesFavoriteVM @Inject constructor(
    context: Application,
    coroutineContextProvider: CoroutineContextProvider,
    private val movieUseCaseImpl: MovieUseCaseImpl
) : BaseVM(context, coroutineContextProvider) {

    private val _movies = MutableLiveData<UIState<List<MoviesUI>>>()
    val movies: LiveData<UIState<List<MoviesUI>>>
        get() = _movies

    fun getAllMoviesFavorite() {
        executeJob {
            _movies.value = UIState.Loading(true)
            try {
                val moviesRaw = movieUseCaseImpl.getAllFavoriteMovies()
                val data = mutableListOf<MoviesUI>().apply { moviesRaw.forEach { add(it.toUI()) } }
                _movies.value = UIState.Success(data)
            } catch (e: Exception) {
                _movies.value = UIState.Failure(e)
            }
            _movies.value = UIState.Loading(false)
        }
    }
}