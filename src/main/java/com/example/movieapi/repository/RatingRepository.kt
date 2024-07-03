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

    @Query(value = "SELECT COUNT(*) FROM Rating r WHERE r.user_id = :userId AND r.movie_id = :movieId", nativeQuery = true)
    fun findResByUserIdAndMovieId(userId: Long, movieId: Long): Int


}