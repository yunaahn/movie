package com.example.movieapi.service

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import com.example.movieapi.entity.MovieWithRating
import org.springframework.stereotype.Service



@Service
interface MovieService{
    fun createMovie(movieDTO: MovieDTO) : MovieDTO

    //fun getMovies() : List<MovieDTO>

    fun getMovie(id: Long) : MovieDTO

    fun deleteMovie(id: Long) : Long

    fun getMovieWithRating(movie_Id: Long): MovieWithRating

    fun searchMoviesByNameContaining(keyword: String, orderBy : String): List<MovieDTO>

    fun getMovies(orderBy : String): List<MovieDTO>
}