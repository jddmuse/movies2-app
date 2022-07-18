package com.example.movies2.di

import android.content.Context
import androidx.room.Room
import com.example.movies2.data.dao.MovieDAO
import com.example.movies2.data.dao.MoviesListDAO
import com.example.movies2.data.database.TheMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val THE_MOVIE_DATABASE = "the_movie_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TheMovieDatabase::class.java, THE_MOVIE_DATABASE).build()

    @Singleton
    @Provides
    fun provideMovieDAO(database: TheMovieDatabase): MovieDAO = database.getMovieDAO()

    @Singleton
    @Provides
    fun provideMoviesListDAO(database: TheMovieDatabase): MoviesListDAO = database.getMoviesListDAO()
}