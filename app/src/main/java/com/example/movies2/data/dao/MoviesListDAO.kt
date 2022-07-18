package com.example.movies2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies2.data.entity.MoviesListEntity

@Dao
interface MoviesListDAO {
    @Query("SELECT * FROM movies_list")
    suspend fun getAllMoviesLists(): List<MoviesListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MoviesListEntity>)

    @Query("DELETE FROM movies_list")
    suspend fun deleteAllMoviesLists()
}