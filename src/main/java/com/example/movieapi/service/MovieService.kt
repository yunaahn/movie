package com.example.movieapi.service

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie

interface MovieService {

    fun createMovie(movieDTO: MovieDTO) : MovieDTO

    fun getMovies() : List<MovieDTO>

    fun getMovie(id: Int) : MovieDTO
}