package com.irfanirawansukirman.network.dao

import androidx.room.Dao
import androidx.room.Query
import com.irfanirawansukirman.network.base.BaseDao
import com.irfanirawansukirman.network.entity.MovieEntity

@Dao
interface MovieDao : BaseDao<MovieEntity> {

    @Query("SELECT * FROM tb_movie where id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM tb_movie where favorite = 't' ORDER BY name ASC")
    suspend fun getAllFavoriteMovies(): List<MovieEntity>

    @Query("DELETE FROM tb_movie")
    suspend fun deleteAllMovies()
}