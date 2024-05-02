package com.example.movieapi.dto

import com.example.movieapi.entity.Rating
import java.io.Serializable

data class RatingDTO(

    val id:Long,

    var user_id:Long?,

    var movie_id: Long,

    var rating:Long
): Serializable

