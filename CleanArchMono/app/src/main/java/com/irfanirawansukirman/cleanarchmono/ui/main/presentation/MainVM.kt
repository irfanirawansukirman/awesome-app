package com.irfanirawansukirman.cleanarchmono.ui.main.presentation

import com.irfanirawansukirman.cleanarchmono.ui.base.BaseVM
import com.irfanirawansukirman.cleanarchmono.ui.base.Error
import com.irfanirawansukirman.cleanarchmono.ui.base.NoInternet
import com.irfanirawansukirman.cleanarchmono.ui.base.Success
import com.irfanirawansukirman.cleanarchmono.ui.main.MainViewEffects
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.MovieInfo

class MainVM(private val moviesUseCase: MoviesUseCase) : BaseVM<MovieInfo, MainViewEffects>() {

    init {
        getMovieList()
    }

    private fun getMovieList() = executeUseCase({
        moviesUseCase("")
            .onSuccess { _uiState.value = Success(it) }
            .onFailure { _uiState.value = Error(it.throwable) }
    }, {
        _uiState.value = NoInternet()
    })

}