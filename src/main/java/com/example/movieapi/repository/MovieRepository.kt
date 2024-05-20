package com.example.movieapi.repository

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : CrudRepository<Movie, Long> {
    @Query("select m from Movie m")
    fun getAllMovies() : List<Movie>

    @Query("select m from Movie m order by m.name asc")
    fun getAllMoviesByName(@Param("keyword") keyword: String) : List<Movie>

    @Query("select m from Movie m order by m.rating desc")
    fun getAllMoviesByRating(@Param("keyword") keyword: String) : List<Movie>

    @Query("select m from Movie m where m.name like %:keyword%")
    fun searchMoviesByNameContaining(@Param("keyword") keyword: String): List<Movie>

}

