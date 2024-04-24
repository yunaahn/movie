package com.example.movieapi.service

import com.example.movieapi.dto.RatingDTO
import com.example.movieapi.repository.RatingRepository
import com.example.movieapi.utils.mapper.RatingMapper
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl (

    private val ratingRepository: RatingRepository,
    private val ratingMapper: RatingMapper
) : RatingService {

    override fun createRating(ratingDTO: RatingDTO): RatingDTO {
       val rating = ratingMapper.toEntity(ratingDTO)
        ratingRepository.save(rating)

        return ratingMapper.fromEntity(rating)
    }


}