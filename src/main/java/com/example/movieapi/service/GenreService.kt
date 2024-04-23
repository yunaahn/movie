package com.example.movieapi.service

import com.example.movieapi.dto.GenreDTO
import com.example.movieapi.entity.Genre

interface GenreService {

    fun createGenre(genreDTO: GenreDTO) : GenreDTO

    fun modifyGenre(genreDTO: GenreDTO)

    fun deleteGenre(genreId: Long)
}