package com.example.movieapi.utils.mapper

import com.example.movieapi.dto.GenreDTO
import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Genre
import com.example.movieapi.entity.Movie
import org.springframework.stereotype.Service

@Service
class GenreMapper : Mapper<GenreDTO, Genre> {

    override fun fromEntity(entity: Genre): GenreDTO = GenreDTO(
        entity.id,
        entity.name

    )


    override fun toEntity(domain: GenreDTO): Genre = Genre(
        domain.id,
        domain.name

    )
}