package com.example.movies2.data.network

import com.example.movies2.data.model.MovieModel
import com.example.movies2.data.model.MoviesListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiClient {

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id:Long): Response<MovieModel?>

    @GET("list/{list_id}")
    suspend fun getMoviesList(@Path("list_id") id:Long) : Response<MoviesListModel?>
}