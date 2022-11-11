package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.Room.MovieDao
import com.example.movieapp.model.Movie

class RoomRepository(private val moviedao: MovieDao) {

    val allmovies: LiveData<List<Movie>> = moviedao.loadallMovies()
    //    val flag:Int=moviedao.getflagfromdb()
    suspend fun insert(movie:Movie){
        moviedao.insertnewmovie(movie)
    }
    suspend fun deletewithtitle(title:String?){
        moviedao.deleteByTitle(title)
    }
    fun getflag(title:String?):Int{
        return  moviedao.getflagfromdb(title)
    }

    fun delete(movie: Movie):Int{
        return moviedao.deleteMovie(movie)
    }
}