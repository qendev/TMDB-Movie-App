package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val api_key: String,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val _Popular: MutableLiveData<List<Movie>> = MutableLiveData()
    val _topRated: MutableLiveData<List<Movie>> = MutableLiveData()
    val _upComing: MutableLiveData<List<Movie>> = MutableLiveData()
    val _nowPlaying: MutableLiveData<List<Movie>> = MutableLiveData()
    val _tv: MutableLiveData<List<Movie>> = MutableLiveData()
    val _trending: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        search()
    }

    fun search() {
        viewModelScope.launch {
            val resultP = movieRepository.getPopular(api_key, 1)
            val resultT = movieRepository.gettoprated(api_key, 1)
            val resultU = movieRepository.getupcoming(api_key, 1)
            val resultN = movieRepository.getnowplaying(api_key, 2)
            val resultV = movieRepository.gettv(api_key, 1)
            val trending = movieRepository.gettrending(api_key)
            _Popular.value = resultP
            _topRated.value = resultT
            _upComing.value = resultU
            _nowPlaying.value = resultN
            _tv.value = resultV
            _trending.value = trending
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("CLEARED","YES")

    }
}