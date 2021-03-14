package com.irfanirawansukirman.movie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irfanirawansukirman.core.util.viewmodel.ViewModelFactory
import com.irfanirawansukirman.core.util.viewmodel.ViewModelKey
import com.irfanirawansukirman.movie.presentation.moviedetail.MovieDetailVM
import com.irfanirawansukirman.movie.presentation.movies.MoviesVM
import com.irfanirawansukirman.movie.presentation.moviesfavorite.MoviesFavoriteVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
abstract class MovieVMModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesVM::class)
    internal abstract fun bindMovieVM(viewModel: MoviesVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailVM::class)
    internal abstract fun bindMovieDetailVM(viewModel: MovieDetailVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesFavoriteVM::class)
    internal abstract fun bindMoviesFavoriteVM(viewModel: MoviesFavoriteVM): ViewModel
}