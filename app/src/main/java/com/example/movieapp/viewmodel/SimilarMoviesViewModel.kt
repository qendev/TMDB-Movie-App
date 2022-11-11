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
class SimilarMoviesViewModel
@Inject
constructor(
    private val api_key: String,
    private val movieRepository: MovieRepository,
) : ViewModel() {
    val similarMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getSimilarMovies(movieid:Int?) {
        viewModelScope.launch {
            val resultp = movieRepository.getsimilarmovies(api_key,movieid, 1)
            Log.i("infoPu","${resultp.size}")
            similarMovies.value=resultp
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SIMILAR CLEARED","YES")
    }


}