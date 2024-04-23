package com.example.movieapi.service

import com.example.movieapi.dto.GenreDTO
import com.example.movieapi.repository.GenreRepository
import com.example.movieapi.utils.mapper.GenreMapper
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl (
    private val genreRepository: GenreRepository,
    private val genreMapper: GenreMapper

) : GenreService {
    override fun createGenre(genreDTO: GenreDTO) :GenreDTO{
       val genre = genreMapper.toEntity(genreDTO)
        genreRepository.save(genre)

        return genreMapper.fromEntity(genre)
    }

    override fun modifyGenre(genreDTO: GenreDTO) {
        val genre = genreMapper.toEntity(genreDTO)

        // 기존 엔터티를 찾아서 수정하고 저장
        val existingGenre = genreRepository.findById(genre.id).orElse(null)
        existingGenre?.let {
            it.name = genre.name
            genreRepository.save(it)
        }

        genreRepository.save(genre)
    }

    override fun deleteGenre(genreId: Long) {
        genreRepository.deleteById(genreId)

    }

}