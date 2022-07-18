package com.example.movies2.domain.model

import com.example.movies2.data.entity.MoviesListEntity
import com.example.movies2.data.model.MoviesListModel
import java.io.Serializable

data class MoviesList(
    val id:Int? = null,
    val description:String = "",
    var results:List<Movie> = emptyList(),
    val backdrop_path:String = "",
    val name:String = "",
    val poster_path:String = ""
) :Serializable

fun MoviesListModel.toDomain() = MoviesList(
    id,
    description,
    results.map { i ->
        i.movies_list_id = id
        i
    }.map { it.toDomain() },
    backdrop_path,
    name,
    poster_path
)

fun MoviesListEntity.toDomain() = MoviesList(
    id = id,
    description = description,
    /*results.map { i ->
        i.movies_list_id = id
        i
    }.map { it.toDomain() },*/
    backdrop_path = backdrop_path,
    name = name,
    poster_path = poster_path
)