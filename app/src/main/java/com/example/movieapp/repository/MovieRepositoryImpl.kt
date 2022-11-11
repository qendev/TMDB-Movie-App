package com.example.movieapp.repository

import com.example.movieapp.model.Movie
import com.example.movieapp.network.model.BackDrops
import com.example.movieapp.network.model.MovieData
import com.example.movieapp.network.model.MovieDetailsMapper
import com.example.movieapp.network.service.MovieService

class MovieRepositoryImpl(private val MovieService: MovieService, private val mapper: MovieDetailsMapper):MovieRepository {

    override suspend fun getPopular(key: String, page: Int): List<Movie> {
        return mapper.toDomainList(MovieService.getPopular(key, page).results)
    }

    override suspend fun getupcoming(key: String, page: Int): List<Movie> {
        return mapper.toDomainList(MovieService.getupcoming(key, page).results)
    }

    override suspend fun gettoprated(key: String, page: Int): List<Movie> {
        return mapper.toDomainList(MovieService.gettoprated(key, page).results)
    }

    override suspend fun getnowplaying(key: String, page: Int): List<Movie> {
        return mapper.toDomainList(MovieService.getnowplaying(key,page).results)
    }

    override suspend fun gettv(key: String, page: Int): List<Movie> {
        return mapper.toDomainList(MovieService.getlatest(key,page).results)
    }

    override suspend fun gettrending(key: String): List<Movie> {
        return  mapper.toDomainList(MovieService.gettrending(key).results)
    }

    override suspend fun getkey(id: Int, key: String): List<MovieData> {
        return   MovieService.getKey(id, key).keys
    }

    override suspend fun getimages(id: Int, key: String): List<BackDrops> {
        return MovieService.getimages(id, key).images
    }

    override suspend fun getmovie(key: String, query: String): List<Movie> {
        return mapper.toDomainList(MovieService.getmovie(key, query).results)
    }

    override suspend fun getsimilarmovies(key: String, id: Int?, page: Int): List<Movie> {
        if(MovieService.getsimilar(id,key,page).isSuccessful){
            return mapper.toDomainList(MovieService.getsimilar(id,key, page).body()!!.results);
        }
        val l:List<Movie> = listOf()
        return l;
    }
}