package com.example.movies2.domain

import android.util.Log
import com.example.movies2.data.network.MovieRepository
import com.example.movies2.domain.model.MoviesList
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    companion object {
        private const val TAG = "GetMoviesListUseCase"
    }

    suspend operator fun invoke(id: Long): MoviesList =
        try {
            Log.i(TAG, "USE_CASE INVOKED: GetMoviesListUseCase()")
            movieRepository.getMoviesListFromAPI(id)
        } catch (ex: Exception) {
            Log.i(TAG, "Exception: $ex")
            MoviesList()
        }

}