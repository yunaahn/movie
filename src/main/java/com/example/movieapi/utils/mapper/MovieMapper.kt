package com.example.movieapi.utils.mapper

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import com.example.movieapi.entity.UploadFile
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class MovieMapper: Mapper<MovieDTO, Movie> {

    override fun fromEntity(entity: Movie): MovieDTO = MovieDTO(
        entity.id,
        entity.name,
        entity.rating,
        entity.genreId,
        attachFile = if (entity.attachFileName != null) {
            UploadFile(entity.attachFileName, entity.attachFileName)
        } else {
            UploadFile("", "")
        }


    )


    override fun toEntity(domain: MovieDTO): Movie = Movie(
        domain.id,
        domain.name,
        domain.rating,
        domain.genreId,
        domain.attachFile.storeFileName

    )
}