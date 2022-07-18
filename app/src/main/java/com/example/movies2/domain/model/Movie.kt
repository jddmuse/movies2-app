package com.example.movies2.domain.model

import com.example.movies2.data.entity.MovieEntity
import com.example.movies2.data.model.MovieModel
import java.io.Serializable

data class Movie(
    val id: Int?,
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int?,
    val original_title: String?,
    val overview: String,
    val title: String,
    val original_language: String?,
    val popularity: Float?,
    val poster_path: String?,
    val release_date: String,
    val tagline: String?,
    val video: Boolean,
    val vote_average: Float?,
    var movies_list_id:Int? = null
): Serializable

fun MovieModel.toDomain() = Movie(
    id,
    adult,
    backdrop_path,
    budget,
    original_title,
    overview,
    title,
    original_language,
    popularity,
    poster_path,
    release_date,
    tagline,
    video,
    vote_average,
    movies_list_id
)

fun MovieEntity.toDomain() = Movie(
    id,
    adult,
    backdrop_path,
    budget,
    original_title,
    overview,
    title,
    original_language,
    popularity,
    poster_path,
    release_date,
    tagline,
    video,
    vote_average,
    movies_list_id
)