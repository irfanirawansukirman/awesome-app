package com.irfanirawansukirman.network.data.service

import com.irfanirawansukirman.network.data.response.MovieResponse
import com.irfanirawansukirman.network.data.response.MoviesGeneralResponse
import com.irfanirawansukirman.network.data.response.MoviesRangeResponse
import com.irfanirawansukirman.network.data.response.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {

    @GET("movie/popular")
    suspend fun getMoviesPopular(@QueryMap param: HashMap<String, Any>): Response<MoviesGeneralResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") param: String
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(@QueryMap param: HashMap<String, Any>): Response<MoviesRangeResponse>

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(@QueryMap param: HashMap<String, Any>): Response<MoviesGeneralResponse>

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(@QueryMap param: HashMap<String, Any>): Response<MoviesRangeResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") param: String
    ): ReviewsResponse
}