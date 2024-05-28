package com.example.movieapi.dto

data class MovieWithAvgRatingDTO (
    val id: Long,
    val name: String,
    val rating: Int,
    val avgRating: Float,
    val genre_id: Long?
)