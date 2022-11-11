package com.example.movieapp.network.responses

import com.example.movieapp.network.model.MovieDetails
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("page")
    var page: Int,

    @SerializedName("results")
    var results: List<MovieDetails>
)
