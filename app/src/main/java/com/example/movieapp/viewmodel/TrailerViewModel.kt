package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Movie
import com.example.movieapp.network.model.BackDrops
import com.example.movieapp.network.model.MovieData
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel
@Inject
constructor(
    private var api_key: String,
    private var repo: MovieRepository,
) : ViewModel() {


    var key: MutableLiveData<List<MovieData>> = MutableLiveData()
    var images: MutableLiveData<List<BackDrops>> = MutableLiveData()
    var movie: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        Log.i("QQQQWW", "IIIIIII${api_key}")
    }

    fun getKey(id: Int) {

        viewModelScope.launch {
            val result = repo.getkey(id, api_key)
            Log.i("priorbro", "${result.size}")
            key.value = result
        }

    }

    fun getimages(id: Int) {
        viewModelScope.launch {
            val result = repo.getimages(id, api_key)
            Log.i("priorbro", "${result.size}")
            images.value = result
        }
    }

    fun getmovie(query:String){
        viewModelScope.launch {
            val result=repo.getmovie(api_key,query)
            movie.value=result
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Cleateed","clearedTrailer")
    }




}