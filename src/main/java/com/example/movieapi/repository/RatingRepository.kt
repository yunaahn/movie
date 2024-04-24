package com.example.movieapi.repository

import com.example.movieapi.entity.Rating
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : CrudRepository<Rating, Long> {}