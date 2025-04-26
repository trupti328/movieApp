package com.trupti.movieapplication.network


import com.trupti.movieapplication.model.Movie

import com.trupti.movieapplication.model.MovieResponse
import com.trupti.movieapplication.model.TvResponse
import com.trupti.movieapplication.model.TvShow

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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


    @GET("title/{id}/details/")
    suspend fun getDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String="aDBJLZnJZ6DLwIjL7wPFjJs0Y3qsvr41HkF9OIix"
    ): Response<Movie>



}