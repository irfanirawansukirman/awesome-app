package com.irfanirawansukirman.movie.presentation.movies

import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.movie.BuildConfig
import com.irfanirawansukirman.movie.data.mapper.MoviesUI
import com.irfanirawansukirman.movie.databinding.MoviesItemBinding

class MoviesItemHolder(private val binding: MoviesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(movies: MoviesUI, onMovieSelected: (MoviesUI) -> Unit) {
        binding.apply {
            ivPoster.loadImageUrl("${BuildConfig.MOVIE_POSTER_BASE_URL}${movies.posterPath}")
            tvTitle.text = movies.title
            tvDate.text = movies.release
            tvOverview.text = movies.overview

            root.setOnClickListener { onMovieSelected(movies) }
        }
    }
}