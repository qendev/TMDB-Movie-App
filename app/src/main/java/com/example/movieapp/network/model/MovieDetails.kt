package com.example.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("vote_average")
    var vote_average: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("name")
    var name:String?=null
)
