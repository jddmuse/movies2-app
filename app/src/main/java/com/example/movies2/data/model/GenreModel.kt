package com.example.movies2.data.model

import java.io.Serializable

data class GenreModel(
    val id:Int?=null,
    val name:String="",
    val movies:List<MovieModel> = emptyList()
): Serializable