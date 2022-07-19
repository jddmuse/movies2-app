package com.example.movies2.data.network

import android.util.Log
import com.example.movies2.data.model.MoviesListModel
import retrofit2.Response
import javax.inject.Inject

class MovieService @Inject constructor(
    private val movieApiClient: MovieApiClient
) {

    companion object {
        private const val TAG = "MovieService"
    }

    suspend fun getMoviesList(id: Int): MoviesListModel =
        try {
            Log.i(TAG, "METHOD CALLED: getMoviesList(Long)")
            val result: Response<MoviesListModel?> = movieApiClient.getMoviesList(id)
            Log.i(TAG, "$result")
            result.body() ?: MoviesListModel()
        } catch (ex: Exception) {
            Log.i(TAG, "Exception: $ex")
            MoviesListModel()
        }
}