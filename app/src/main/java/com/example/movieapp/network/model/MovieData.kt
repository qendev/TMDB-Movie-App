package com.example.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("iso_639_1")
    var iso: String? = null,
    @SerializedName("iso_3166_1")
    var iso1: String? = null,
    @SerializedName("key")
    var key: String ,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: String? = null,
)
