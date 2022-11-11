package com.example.movieapp.di

import com.example.movieapp.network.model.MovieDetailsMapper
import com.example.movieapp.network.service.MovieService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieMapper():MovieDetailsMapper{
        return MovieDetailsMapper()
    }
    @Singleton
    @Provides
    fun provideMovieService():MovieService{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieService::class.java)
    }
}