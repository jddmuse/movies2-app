package com.example.movies2.vews.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies2.domain.GetMoviesByNameUseCase
import com.example.movies2.domain.GetMoviesListUseCase
import com.example.movies2.domain.model.Movie
import com.example.movies2.domain.model.MoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getMoviesByNameUseCase: GetMoviesByNameUseCase
):ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _isLoading = true
    val isLoading = MutableLiveData<Boolean>()

    private var isInfoGot: Boolean = false
    val generalMoviesLists = MutableLiveData<List<MoviesList>?>()

    // search list
    val searchMoviesList = MutableLiveData<List<Movie>>()

    fun onCreate(){
        getMoviesLists()
    }

    private fun getMoviesLists(){
        viewModelScope.launch {
            try {
                Log.i(TAG, "METHOD CALLED: getMoviesLists()")
                if(!isInfoGot) {
                    // invoke use case
                    val result: ArrayList<MoviesList> = getMoviesListUseCase()

                    // changing LiveData values
                    generalMoviesLists.value = result
                    isLoading.value = !_isLoading

                    // changing _isInfoGot state
                    isInfoGot = !isInfoGot
                }
            } catch (ex:Exception) {
                Log.e(TAG, "Exception $ex")
            }
        }
    }

    fun getMoviesByName(query:String?){
        viewModelScope.launch {
            try {
                Log.i(TAG, "METHOD CALLED: getMoviesByName()")
                if(!query.isNullOrEmpty()) {
                    val result: List<Movie> = getMoviesByNameUseCase(query.lowercase())
                    if(!result.isNullOrEmpty())
                        searchMoviesList.value = result
                    else
                        searchMoviesList.value = emptyList()
                } else
                    searchMoviesList.value = emptyList()

            } catch (ex:Exception) {
                Log.e(TAG, "Excepton: $ex")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "METHOD CALLED: onCleared()")
    }
}