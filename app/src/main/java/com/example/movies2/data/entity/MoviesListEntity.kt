package com.example.movies2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.movies2.domain.model.MoviesList

@Entity(tableName = "movies_list")
data class MoviesListEntity(
    @PrimaryKey
    @ColumnInfo(name="movies_list_id") val id:Int?,
    @ColumnInfo(name="description") val description:String = "",
    //@ColumnInfo(name="movies") var results:List<Int> = emptyList(),
    @ColumnInfo(name="backdrop_path") val backdrop_path:String = "",
    @ColumnInfo(name="name") val name:String = "",
    @ColumnInfo(name="poster_path") val poster_path:String = ""
)

fun MoviesList.toDatabase() = MoviesListEntity(
    id,
    description,
    //results.map { it.id!!.toInt() },
    backdrop_path,
    name,
    poster_path
)