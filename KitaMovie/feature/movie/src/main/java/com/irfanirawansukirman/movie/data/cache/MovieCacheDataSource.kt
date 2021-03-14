package com.irfanirawansukirman.movie.data.cache

import com.irfanirawansukirman.movie.data.contract.IMovieCacheDataSource
import com.irfanirawansukirman.network.dao.MovieDao
import com.irfanirawansukirman.network.entity.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCacheDataSource @Inject constructor(private val movieDao: MovieDao) :
    IMovieCacheDataSource {

    override suspend fun insertMovie(movieEntity: MovieEntity) {
        movieDao.insert(movieEntity)
    }

    override suspend fun getMovieById(movieId: Int): MovieEntity = movieDao.getMovieById(movieId)

    override suspend fun getAllFavoriteMovies(): List<MovieEntity> = movieDao.getAllFavoriteMovies()
}