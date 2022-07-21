package com.example.movies2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies2.data.entity.MovieEntity

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movie")
    suspend fun getAllMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items:List<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie WHERE title LIKE :q")
    suspend fun getMoviesByName(q:String):List<MovieEntity>

}