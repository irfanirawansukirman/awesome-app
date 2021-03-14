package com.irfanirawansukirman.movie.presentation.movies

sealed class MoviesCategoryState {
    object Popular : MoviesCategoryState()
    object Upcoming : MoviesCategoryState()
    object TopRated : MoviesCategoryState()
    object NowPlaying : MoviesCategoryState()
}
