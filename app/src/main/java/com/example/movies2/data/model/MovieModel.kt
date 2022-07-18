package com.example.movies2.data.model

import java.io.Serializable

const val baseUrlImg = "http://image.tmdb.org/t/p/w500"

data class MovieModel(
    val id:Int? = null,
    val adult:Boolean = false,
    val backdrop_path:String? = "",
    val budget:Int? = null,
    val original_title:String = "",
    val overview:String ="",
    val title:String ="",
    var genres:List<GenreModel> = emptyList(),
    val original_language:String? = "",
    val popularity:Float? = null,
    val poster_path:String ="",
    val production_companies:List<ProductionCompanyModel> = emptyList(),
    val release_date:String = "",
    val tagline:String? = "",
    val video:Boolean = false,
    val vote_average:Float? = null,
    var movies_list_id:Int? = null
): Serializable