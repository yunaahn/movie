package com.example.movieapi.web.rest

import com.example.movieapi.dto.MovieDTO
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
    private val movieService: MovieService
) {

    private final val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/add")
    fun createMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO> {
        val gen = movieDTO.genre_id;
        logger.debug("genre =",gen)
        return ResponseEntity(movieService.createMovie(movieDTO), HttpStatus.OK)
    }

    @GetMapping("/list")
    fun getMovies(): ResponseEntity<List<MovieDTO>> {
        return ResponseEntity(movieService.getMovies(), HttpStatus.OK)
    }

    @GetMapping("/list/{id}")
    fun getMovie(@PathVariable id: Long)  {
        ResponseEntity.ok(movieService.getMovie(id))
    }
}