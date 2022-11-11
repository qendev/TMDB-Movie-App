package com.example.movieapp.repository

import com.example.movieapp.model.Movie
import com.example.movieapp.network.model.BackDrops
import com.example.movieapp.network.model.MovieData

interface MovieRepository {

    suspend fun getPopular(key: String, page: Int): List<Movie>
    suspend fun getupcoming(key: String, page: Int): List<Movie>
    suspend fun gettoprated(key: String, page: Int ): List<Movie>
    suspend fun getnowplaying(key: String, page: Int ): List<Movie>
    suspend fun gettv(key: String, page: Int ): List<Movie>
    suspend fun gettrending(key: String): List<Movie>
    suspend fun getkey(id:Int,key: String): List<MovieData>
    suspend fun getimages(id:Int,key: String): List<BackDrops>
    suspend fun getmovie(key: String,query:String):List<Movie>
    suspend fun getsimilarmovies(key: String,id:Int?,page:Int):List<Movie>

}