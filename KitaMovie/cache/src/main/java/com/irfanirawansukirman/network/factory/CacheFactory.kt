package com.irfanirawansukirman.network.factory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfanirawansukirman.network.dao.MovieDao
import com.irfanirawansukirman.network.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 8, exportSchema = false)
abstract class CacheFactory : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}