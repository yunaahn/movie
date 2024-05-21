package com.example.movieapi.web.rest

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import com.example.movieapi.entity.MovieWithRating
import com.example.movieapi.repository.RatingRepository
import com.example.movieapi.service.MovieService
import lombok.extern.log4j.Log4j2
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Log4j2
@RestController
@RequestMapping("/movie")
class MovieController (
    private val movieService: MovieService,
    private val ratingRepository: RatingRepository
) {

    private final val logger = LoggerFactory.getLogger(javaClass)
    //영화 추가
    @PostMapping("/add")
    fun createMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO> {
        val gen = movieDTO.genre_id;
        logger.debug("genre =",gen)

        return ResponseEntity(movieService.createMovie(movieDTO), HttpStatus.OK)
    }
    //영화 리스트 -> 장르, 평점 별로 조회 추가
    @GetMapping("/list")
    fun getMovies(@RequestParam orderBy: String): ResponseEntity<List<MovieDTO>> {
        return ResponseEntity(movieService.getMovies(orderBy), HttpStatus.OK)
    }
    //id 별 리스트 -> 평점 함께 조회되는 걸 추가하고 싶음
    @GetMapping("/list/{id}")
    fun getMovie(@PathVariable id: Long): MovieWithRating {
        val list = movieService.getMovie(id)

        return movieService.getMovieWithRating(id)
    }
    // 삭제
    @DeleteMapping("/delete/{id}")
    fun deleteMovie(@PathVariable id: Long): ResponseEntity<Long> {
        return ResponseEntity(movieService.deleteMovie(id), HttpStatus.OK)

    }

    //검색
    @GetMapping("/search")
    fun searchMovie(@RequestParam keyword: String,
                    @RequestParam orderBy: String) : List<MovieDTO> {
        return movieService.searchMoviesByNameContaining(keyword, orderBy)
    }


}