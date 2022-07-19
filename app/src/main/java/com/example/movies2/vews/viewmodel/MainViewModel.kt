package com.example.movies2.vews.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies2.domain.GetMoviesListUseCase
import com.example.movies2.domain.model.Movie
import com.example.movies2.domain.model.MoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
):ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _isLoading = true
    val isLoading = MutableLiveData<Boolean>()

    private var isInfoGot: Boolean = false
    val generalMoviesLists = MutableLiveData<List<MoviesList>?>()

    fun onCreate(){
        getMoviesLists()
    }

    private fun getMoviesLists(){
        viewModelScope.launch {
            try {

            } catch (ex:Exception) {
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
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "METHOD CALLED: onCleared()")
    }
}