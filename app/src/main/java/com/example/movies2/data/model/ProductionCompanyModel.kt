package com.example.movies2.data.model

import java.io.Serializable

data class ProductionCompanyModel(
    val id:Int?=null,
    val logo_path:String="",
    val name:String="",
    val origin_Country:String=""
): Serializable