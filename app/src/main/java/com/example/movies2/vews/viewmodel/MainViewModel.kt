package com.example.movies2.vews.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies2.domain.GetMoviesListUseCase
import com.example.movies2.domain.model.Movie
import com.example.movies2.domain.model.MoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase
):ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _topRatedMovies: ArrayList<Movie> = arrayListOf()
    private val _morePopular : ArrayList<Movie> = arrayListOf()
    private var isInfoGot: Boolean = false

    private val _generalMoviesLists: ArrayList<MoviesList> = arrayListOf()
    val generalMoviesLists = MutableLiveData<List<MoviesList>?>()

    fun onCreate(){
        getMoviesLists()
    }

    private fun getMoviesLists(){
        viewModelScope.launch {
            Log.i(TAG, "METHOD CALLED: getMoviesLists()")
            if(!isInfoGot) {
                for (i in 1..5){
                    val result: MoviesList = getMoviesListUseCase(i.toLong())
                    // if result.id isn't null, there is data
                    if(result.id != null) {
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
                _generalMoviesLists.add(0, MoviesList(name = "More Popular", results = _morePopular.sortedByDescending { it.popularity }))
                _generalMoviesLists.add(1, MoviesList(name = "Top Rated", results = _topRatedMovies.sortedByDescending { it.vote_average }))

                // changing LiveData values
                generalMoviesLists.value = _generalMoviesLists

                // changing _isInfoGot state
                isInfoGot = !isInfoGot
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "METHOD CALLED: onCleared()")
    }
}