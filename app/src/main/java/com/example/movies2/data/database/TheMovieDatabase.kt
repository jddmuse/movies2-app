package com.example.movies2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies2.data.dao.MovieDAO
import com.example.movies2.data.dao.MoviesListDAO
import com.example.movies2.data.entity.MovieEntity
import com.example.movies2.data.entity.MoviesListEntity

@Database(entities = [MovieEntity::class, MoviesListEntity::class], version = 1)
abstract class TheMovieDatabase: RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
    abstract fun getMoviesListDAO(): MoviesListDAO
}