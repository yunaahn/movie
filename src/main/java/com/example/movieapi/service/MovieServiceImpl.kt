package com.example.movieapi.service

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.dto.RatingDTO
import com.example.movieapi.entity.Movie
import com.example.movieapi.entity.MovieWithRating
import com.example.movieapi.entity.Rating
import com.example.movieapi.repository.MovieRepository
import com.example.movieapi.repository.MovieRepositoryImpl
import com.example.movieapi.repository.RatingRepository
import com.example.movieapi.utils.mapper.MovieMapper
import com.example.movieapi.utils.mapper.RatingMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.text.DecimalFormat

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val movieMapper: MovieMapper,
    private val ratingRepository: RatingRepository,
    private val ratingService: RatingService
) : MovieService {

    val log = LoggerFactory.getLogger(MovieServiceImpl::class.java)!!
    override fun createMovie(movieDTO: MovieDTO) : MovieDTO{
        val existingMovie = movieRepository.findById(movieDTO.id)
        if (existingMovie.isPresent) {
            throw RuntimeException("Movie with ID ${movieDTO.id} already exists.")
        }

        val movie = movieMapper.toEntity(movieDTO)
        movieRepository.save(movie)


        return movieMapper.fromEntity(movie)
    }


    override fun getMovies(orderBy : String): List<MovieDTO> {
        val movies = when (orderBy) {
            "name" -> movieRepositoryImpl.movieListByName(orderBy)
            "rating" -> movieRepositoryImpl.movieListByRating(orderBy)
            else -> movieRepositoryImpl.movieList(orderBy)
        }
        return movies.map { movieMapper.fromEntity(it) }
    }


    override fun getMovie(id: Long): MovieDTO {
        return movieMapper.fromEntity(movieRepository.findById(id).get())
    }

    override fun deleteMovie(id: Long): Long {
        movieRepository.deleteById(id)
        return id
    }


    override fun getMovieWithRating(movie_Id: Long): MovieWithRating  {
        val movie = movieRepository.findById(movie_Id).orElseThrow { NoSuchElementException("Movie not found") }
        val movieDTO = movieMapper.fromEntity(movie)

        val ratings = ratingRepository.findByMovieId(movie_Id)
        log.info("movie_Id =" + movie_Id)

        val averageRating = ratings.map { it.rating }.average().toDouble()
        val formattedRating = DecimalFormat("#.##").format(averageRating)
        log.info("ratings =" + ratings)
        log.info("averageRating =" + formattedRating)
        return MovieWithRating(movieDTO, formattedRating)
    }

    override fun searchMoviesByNameContaining(keyword: String, orderBy : String): List<MovieDTO> {
        val result = hashMapOf<Long, MovieDTO>();
        val movies = when (orderBy) {
            "name" -> movieRepositoryImpl.findAllMoviesByName(keyword)
            "rating" -> movieRepositoryImpl.findAllMoviesByRating(keyword)
            else -> movieRepositoryImpl.findAllMovies()
        }
        return movies.map { movieMapper.fromEntity(it) }
    }
}