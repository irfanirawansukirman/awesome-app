package com.irfanirawansukirman.movie.presentation.movies

/**
 * Created by Irfan Irawan Sukirman on 3/10/21 at Bandung City
 * Copyright (c) 2021 PT. Haruka Evolusi Digital Utama. All rights reserved.
 */
sealed class MoviesCategoryState {
    object Popular : MoviesCategoryState()
    object Upcoming : MoviesCategoryState()
    object TopRated : MoviesCategoryState()
    object NowPlaying : MoviesCategoryState()
}
