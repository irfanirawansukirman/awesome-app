package com.irfanirawansukirman.network.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release")
    val release: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "favorite")
    val favorite: String?
)