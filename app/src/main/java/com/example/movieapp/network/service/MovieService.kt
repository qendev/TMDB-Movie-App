package com.example.movieapp.network.service

import com.example.movieapp.network.responses.MovieSearchResponse
import com.example.movieapp.network.responses.MovieSearchResponse2
import com.example.movieapp.network.responses.MovieSearchResponse3
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): MovieSearchResponse

    @GET("3/movie/top_rated")
    suspend fun gettoprated(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): MovieSearchResponse

    @GET("3/movie/upcoming")
    suspend fun getupcoming(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): MovieSearchResponse

    @GET("3/movie/now_playing")
    suspend fun getnowplaying(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): MovieSearchResponse

    @GET("3/tv/popular")
    suspend fun getlatest(
        @Query("api_key") key: String,
        @Query("page") page: Int,
    ): MovieSearchResponse

    @GET("3/trending/movie/day")
    suspend fun gettrending(
        @Query("api_key") key: String,
    ): MovieSearchResponse

    @GET("3/movie/{id}/videos")
    suspend fun getKey(
        @Path("id") id:Int,
        @Query("api_key") key: String,
    ): MovieSearchResponse2

    @GET("3/movie/{id}/images")
    suspend fun getimages(
        @Path("id") id:Int,
        @Query("api_key") key: String,
    ): MovieSearchResponse3

    @GET("3/search/movie")
    suspend fun getmovie(
        @Query("api_key") key: String,
        @Query("query") query:String
    ): MovieSearchResponse

    @GET("3/movie/{id}/similar")
    suspend fun getsimilar(
        @Path("id") id:Int?,
        @Query("api_key") key: String,
        @Query("page") page:Int
    ): Response<MovieSearchResponse>

}