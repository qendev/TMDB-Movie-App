package com.example.movieapp.di

import com.example.movieapp.network.model.MovieDetailsMapper
import com.example.movieapp.network.service.MovieService
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        MovieService: MovieService,
        movieDetailsMapper: MovieDetailsMapper
    ): MovieRepository {
        return MovieRepositoryImpl(MovieService, movieDetailsMapper)
    }
}