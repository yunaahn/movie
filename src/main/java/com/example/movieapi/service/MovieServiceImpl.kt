package com.example.movieapi.service

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import com.example.movieapi.repository.MovieRepository
import com.example.movieapi.utils.mapper.MovieMapper
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : MovieService {
    override fun createMovie(movieDTO: MovieDTO) : MovieDTO{
        val existingMovie = movieRepository.findById(movieDTO.id)
        if (existingMovie.isPresent) {
            throw IllegalArgumentException("Movie with ID ${movieDTO.id} already exists")
        }

        val movie = movieMapper.toEntity(movieDTO)

        movieRepository.save(movie)

        return movieMapper.fromEntity(movie)
    }

    override fun getMovies(): List<MovieDTO> {
        val movies = movieRepository.getAllMovies()

        if (movies.isEmpty())
            throw Exception("There is no movies to display")

        return movies.map {
            movieMapper.fromEntity(it)
        }

    }


    override fun getMovie(id: Long): MovieDTO {
        return movieMapper.fromEntity(movieRepository.findById(id).get())
    }
}