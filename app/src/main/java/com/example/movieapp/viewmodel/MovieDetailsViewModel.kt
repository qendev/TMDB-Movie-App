package com.example.movieapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Room.MovieDataBase
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application):AndroidViewModel(application) {
    val roomRepository: RoomRepository
    val allmovies: LiveData<List<Movie>>
    var flag: Int = 0

    init {
        val dao = MovieDataBase.getdatabase(application).moviedao()
        roomRepository = RoomRepository(dao)
        allmovies = roomRepository.allmovies
    }

    fun addMovie(movies: Movie) = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.insert(movies)
    }

    fun deleteMovieWithTitle(title: String?) = viewModelScope.launch(Dispatchers.IO) {
        roomRepository.deletewithtitle(title)
    }

    fun getflagfromtitle(title: String?): Int {
        val moviewTile = roomRepository.getflag(title)
        Log.i("CHECKING TITLE", "$moviewTile")
        return moviewTile
    }

    fun delete(movies: Movie): Int {
        return  roomRepository.delete(movies)
    }

    override fun onCleared() {
        super.onCleared()
    }
}