package com.example.movieapi.repository

import com.example.movieapi.entity.Rating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<Rating, Long> {
    @Query("select r from Rating r where r.movie_id = :movie_Id")
    fun findByMovieId(movie_Id: Long): List<Rating>

}