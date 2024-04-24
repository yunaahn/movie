package com.example.movieapi.service

import com.example.movieapi.dto.RatingDTO
import com.example.movieapi.entity.Rating

interface RatingService {

    fun createRating(ratingDTO: RatingDTO): RatingDTO

}