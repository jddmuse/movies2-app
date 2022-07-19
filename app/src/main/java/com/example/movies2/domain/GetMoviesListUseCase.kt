package com.example.movies2.domain

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.movies2.data.entity.toDatabase
import com.example.movies2.data.network.MovieRepository
import com.example.movies2.domain.model.Movie
import com.example.movies2.domain.model.MoviesList
import com.example.movies2.util.CheckNetwork
import java.lang.Exception
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val checkNetwork: CheckNetwork,
    private val context: Context
) {

    companion object {
        private const val TAG = "GetMoviesListUseCase"
    }

    private val _topRatedMovies: ArrayList<Movie> = arrayListOf()
    private val _morePopular : ArrayList<Movie> = arrayListOf()
    private val _generalMoviesLists: ArrayList<MoviesList> = arrayListOf()

    suspend operator fun invoke(): ArrayList<MoviesList> =
        try {
            Log.i(TAG, "USE_CASE INVOKED: GetMoviesListUseCase()")
            if(checkNetwork.isOnline(context)){
                // clean room data
                movieRepository.clearAllMovies()

                // get info from API
                for (i in 1..5){
                    val result: MoviesList = movieRepository.getMoviesListFromAPI(i)
                    // if result.id isn't null, there is data
                    if(result.id != null && result.results.isNotEmpty()) {
                        // save data into room
                        movieRepository.insertAllMoviesInRoom(result.results.map { it.toDatabase() })

                        _generalMoviesLists.add(result)
                        val movies: MutableList<Movie> = result.results as MutableList<Movie>

                        // getting top Rated from current MoviesList
                        val topRated = movies.sortedByDescending { it.vote_average }.subList(0, 2)
                        _topRatedMovies.addAll(topRated)

                        // getting more popular from current MoviesList
                        val morePopular = movies.sortedByDescending { it.popularity }.subList(0, 2)
                        _morePopular.addAll(morePopular)
                    }
                }

                // adding topRated and MorePopular lists in generalMoviesLists
                _generalMoviesLists.add(0, MoviesList(
                    name = "More Popular", results = _morePopular.sortedByDescending { it.popularity })
                )
                _generalMoviesLists.add(1, MoviesList(
                    name = "Top Rated", results = _topRatedMovies.sortedByDescending { it.vote_average })
                )

            } else {
                _generalMoviesLists.add(
                    MoviesList(name = "All", results = movieRepository.getMoviesListFromLocal())
                )
            }
            // return
            _generalMoviesLists

            } catch (ex:Exception) {
                Log.e(TAG, "Exception: $ex")
                _generalMoviesLists
            }

}