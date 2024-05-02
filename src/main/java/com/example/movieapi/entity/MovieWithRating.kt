package com.example.movieapi.entity

import com.example.movieapi.dto.MovieDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


data class MovieWithRating(
    val movie: MovieDTO,
    val averageRating: String
)