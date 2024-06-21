package com.example.movieapi.repository

import com.example.movieapi.entity.Rating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RatingRepository : JpaRepository<Rating, Long> {
    @Query("select r from Rating r where r.movieId = :movie_Id")
    fun findByMovieId(movie_Id: Long): List<Rating>

    fun findByUserIdAndMovieId(userId: Long?, movieId: Long?): Optional<Rating?>?

}