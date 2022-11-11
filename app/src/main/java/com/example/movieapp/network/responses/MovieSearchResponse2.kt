package com.example.movieapp.network.responses

import com.example.movieapp.network.model.MovieData
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse2(
    @SerializedName("id")
    var id:Int,
    @SerializedName("results")
    var keys : List<MovieData>
)
