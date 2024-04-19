package com.example.movieapi.dto

data class RatingDTO(

    val id:Long,

    val user_id:Long,

    val movie_id: Long,

    val rating:Int
)
