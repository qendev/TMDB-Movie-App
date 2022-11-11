package com.example.movieapp.network.model

import com.example.movieapp.model.Movie
import com.example.movieapp.util.DomainMapper

class MovieDetailsMapper:DomainMapper<MovieDetails,Movie> {

    override fun mapFromDomainModel(domainModel: Movie): MovieDetails {
        return MovieDetails(
            domainModel.Movieid,
            domainModel.Title,
            domainModel.Poster,
            domainModel.BackImage,
            domainModel.Vote,
            domainModel.Overview
        )
    }

    override fun mapToDomainModel(model: MovieDetails): Movie {
        val title=if(model.title==null) model.name else model.title
        return  Movie(
            0,0,
            model.id,
            title ,
            model.vote_average,
            model.overview,
            model.backdrop_path,
            model.poster_path
        )
    }
    fun toDomainList(initial: List<MovieDetails>): List<Movie>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Movie>): List<MovieDetails>{
        return initial.map { mapFromDomainModel(it) }
    }
}