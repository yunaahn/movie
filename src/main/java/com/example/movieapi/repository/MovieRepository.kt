package com.example.movieapi.repository

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MovieRepository : CrudRepository<Movie, Long> {
    @Query("select m from Movie m")
    fun getAllMovies() : List<Movie>

}

