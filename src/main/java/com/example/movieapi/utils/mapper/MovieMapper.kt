package com.example.movieapi.utils.mapper

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class MovieMapper: Mapper<MovieDTO, Movie> {
//    override fun fromEntity(entity: Movie): MovieDTO {
//        return MovieDTO(
//            entity.id,
//            entity.name,
//            entity.rating
//        )
//
//    }

    override fun fromEntity(entity: Movie): MovieDTO = MovieDTO(
        entity.id,
        entity.name,
       entity.rating,
        entity.genre_id,


    )


    override fun toEntity(domain: MovieDTO): Movie = Movie(
        domain.id,
        domain.name,
       domain.rating,
        domain.genre_id

    )
}