package com.example.movieapi.repository

import com.example.movieapi.entity.Movie

interface CustomMovieRepository {

    fun findAllMovies(): List<Movie>
    fun findAllMoviesByName(keyword: String): List<Movie>
    fun findAllMoviesByRating(keyword: String): List<Movie>
    fun searchMoviesByNameContaining(keyword: String): List<Movie>
    fun movieList(orderBy : String): List<Movie>
    fun movieListByRating(orderBy: String): List<Movie>
    fun movieListByName(orderBy: String): List<Movie>
}