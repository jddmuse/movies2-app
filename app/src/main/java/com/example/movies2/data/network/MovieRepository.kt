package com.example.movies2.data.network

import android.util.Log
import com.example.movies2.data.model.MoviesListModel
import com.example.movies2.domain.model.MoviesList
import com.example.movies2.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    companion object {
        private const val TAG = "MovieRepository"
    }

    suspend fun getMoviesListFromAPI(id: Long): MoviesList =
        withContext(Dispatchers.IO) {
            try {
                Log.i(TAG, "METHOD CALLED: getMoviesListFromAPI(long)")
                val result: MoviesListModel = movieService.getMoviesList(id)
                Log.i(TAG, "$result")
                result.toDomain()
            } catch (ex: Exception) {
                Log.i(TAG, "Exception: $ex")
                MoviesList()
            }
        }
}