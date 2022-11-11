package com.example.movieapp.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.model.Movie
@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie_Table ")
    fun loadallMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertnewmovie(movie: Movie?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie?)

    @Delete
    fun deleteMovie(movie: Movie?): Int

    @Query("DELETE FROM Movie_Table WHERE Title = :title")
    fun deleteByTitle(title: String?): Int

    @Query("SELECT flag FROM Movie_Table Where Title=:title")
    fun getflagfromdb(title: String?): Int
}