package com.irfanirawansukirman.movie.presentation.movies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfanirawansukirman.core.base.BaseVM
import com.irfanirawansukirman.core.ui.UIState
import com.irfanirawansukirman.core.util.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.movie.data.mapper.MoviesUI
import com.irfanirawansukirman.movie.data.mapper.toUI
import com.irfanirawansukirman.movie.domain.MovieUseCaseImpl
import com.irfanirawansukirman.network.util.getViewStateFlowForNetworkCall
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MoviesVM @Inject constructor(
    context: Application,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val movieUseCaseImpl: MovieUseCaseImpl
) : BaseVM(context, coroutineContextProvider) {

    private val _movies = MutableLiveData<UIState<List<MoviesUI>>>()
    val movies: LiveData<UIState<List<MoviesUI>>>
        get() = _movies

    private val _categoryState = MutableLiveData<MoviesCategoryState>()
    val categoryState: LiveData<MoviesCategoryState>
        get() = _categoryState

    fun getMoviesPopular(param: HashMap<String, Any>) {
        executeJob {
            getViewStateFlowForNetworkCall(coroutineContextProvider) {
                movieUseCaseImpl.getMoviesPopular(param)
            }.collect {
                switchToMain {
                    when (it) {
                        is UIState.Loading -> _movies.value = it
                        is UIState.Success -> {
                            val movies = it.output.results
                            val dataUI = mutableListOf<MoviesUI>().apply {
                                if (movies != null && movies.isNotEmpty()) {
                                    movies.forEach { movie -> add(movie.toUI()) }
                                } else {
                                    addAll(emptyList())
                                }
                            }
                            _movies.value = UIState.Success(dataUI)
                        }
                        is UIState.Failure -> _movies.value = it
                    }
                }
            }
        }
    }

    fun setupCategory(state: MoviesCategoryState) {
        _categoryState.value = state
    }

    fun getMoviesUpcoming(param: HashMap<String, Any>) {
        executeJob {
            getViewStateFlowForNetworkCall(coroutineContextProvider) {
                movieUseCaseImpl.getMoviesUpcoming(param)
            }.collect {
                switchToMain {
                    when (it) {
                        is UIState.Loading -> _movies.value = it
                        is UIState.Success -> {
                            val movies = it.output.results
                            val dataUI = mutableListOf<MoviesUI>().apply {
                                if (movies != null && movies.isNotEmpty()) {
                                    movies.forEach { movie -> add(movie.toUI()) }
                                } else {
                                    addAll(emptyList())
                                }
                            }
                            _movies.value = UIState.Success(dataUI)
                        }
                        is UIState.Failure -> _movies.value = it
                    }
                }
            }
        }
    }

    fun getMoviesTopRated(param: HashMap<String, Any>) {
        executeJob {
            getViewStateFlowForNetworkCall(coroutineContextProvider) {
                movieUseCaseImpl.getMoviesTopRated(param)
            }.collect {
                switchToMain {
                    when (it) {
                        is UIState.Loading -> _movies.value = it
                        is UIState.Success -> {
                            val movies = it.output.results
                            val dataUI = mutableListOf<MoviesUI>().apply {
                                if (movies != null && movies.isNotEmpty()) {
                                    movies.forEach { movie -> add(movie.toUI()) }
                                } else {
                                    addAll(emptyList())
                                }
                            }
                            _movies.value = UIState.Success(dataUI)
                        }
                        is UIState.Failure -> _movies.value = it
                    }
                }
            }
        }
    }

    fun getMoviesNowPlaying(param: HashMap<String, Any>) {
        executeJob {
            getViewStateFlowForNetworkCall(coroutineContextProvider) {
                movieUseCaseImpl.getMoviesNowPlaying(param)
            }.collect {
                switchToMain {
                    when (it) {
                        is UIState.Loading -> _movies.value = it
                        is UIState.Success -> {
                            val movies = it.output.results
                            val dataUI = mutableListOf<MoviesUI>().apply {
                                if (movies != null && movies.isNotEmpty()) {
                                    movies.forEach { movie -> add(movie.toUI()) }
                                } else {
                                    addAll(emptyList())
                                }
                            }
                            _movies.value = UIState.Success(dataUI)
                        }
                        is UIState.Failure -> _movies.value = it
                    }
                }
            }
        }
    }
}