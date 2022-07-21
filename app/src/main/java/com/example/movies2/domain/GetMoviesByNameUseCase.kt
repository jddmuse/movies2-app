package com.example.movies2.domain

import android.util.Log
import com.example.movies2.data.network.MovieRepository
import com.example.movies2.domain.model.Movie
import javax.inject.Inject

class GetMoviesByNameUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    companion object {
        private const val TAG = "GetMoviesByName"
    }

    suspend operator fun invoke(query:String):List<Movie> =
        try {
            Log.i(TAG, "USE_CASE INVOKED: GetMoviesByNameUseCase(String)")
            val result = movieRepository.getMoviesByName(query)
            Log.i(TAG, "$result")
            result
        } catch (ex:Exception) {
            Log.e(TAG, "Execption: $ex")
            emptyList<Movie>()
        }

}