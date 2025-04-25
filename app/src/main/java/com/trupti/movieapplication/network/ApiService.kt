package com.trupti.movieapplication.network

import com.trupti.movieapplication.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list-titles/")
    suspend fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String = "movie",
        @Query("sort_by") sortBy: String = "popularity_desc"
    ): Response<MovieResponse>


    @GET("list-titles/")
    suspend fun getTvShows(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String = "tv_series",
        @Query("sort_by") sortBy: String = "popularity_desc"
    ): Response<TvResponse>

}