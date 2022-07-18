package com.example.movies2.data.model

import com.example.movies2.data.model.MovieModel
import java.io.Serializable

data class MoviesListModel(
    val id:Int? = null,
    val description:String = "",
    var results:List<MovieModel> = emptyList(),
    val backdrop_path:String = "",
    val name:String = "",
    val poster_path:String = ""
):Serializable