package com.example.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class BackDrops(
    @SerializedName("file_path")
    var filepath:String,
    @SerializedName("vote_average")
    var vote:Double?=null
)
