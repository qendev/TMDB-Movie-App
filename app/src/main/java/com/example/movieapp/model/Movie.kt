package com.example.movieapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "Movie_Table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val flag: Int =0,
    val Movieid: Int? = null,
    val Title: String? = null,
    val Vote: String? = null,
    val Overview: String? = null,
    val BackImage: String? = null,
    val Poster: String? = null,
    val name: String? = null,
) : Parcelable