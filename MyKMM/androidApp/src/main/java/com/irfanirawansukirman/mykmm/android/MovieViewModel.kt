package com.irfanirawansukirman.mykmm.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.mykmm.ApiClient
import com.irfanirawansukirman.mykmm.MovieUseCase
import com.irfanirawansukirman.mykmm.Result
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val useCase = MovieUseCase(ApiClient())

    private val _movie = MutableLiveData<State<List<Result>>>()
    val movie: LiveData<State<List<Result>>>
        get() = _movie

    fun getAllMovie() {
        viewModelScope.launch {
            _movie.value = State.Loading
            runCatching { useCase.getAllMovies() }
                .onSuccess { _movie.value = State.Success(it.results) }
                .onFailure { _movie.value = State.Error }
        }
    }
}