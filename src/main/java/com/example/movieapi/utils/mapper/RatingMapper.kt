package com.example.movieapi.utils.mapper

import com.example.movieapi.dto.GenreDTO
import com.example.movieapi.dto.RatingDTO
import com.example.movieapi.entity.Genre
import com.example.movieapi.entity.Rating
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Service
class RatingMapper : Mapper<RatingDTO, Rating> {

    override fun fromEntity(entity: Rating): RatingDTO = RatingDTO(
        entity.id,
        entity.userId,
        entity.movieId,
        entity.rating

    )


    override fun toEntity(domain: RatingDTO): Rating = Rating(
        domain.id,
        domain.user_id,
        domain.movie_id,
        domain.rating

    )
}