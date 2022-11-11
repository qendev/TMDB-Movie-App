package com.example.movieapp.network.responses

import com.example.movieapp.network.model.BackDrops
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse3(
    @SerializedName("backdrops")
    var images:List<BackDrops>
)
