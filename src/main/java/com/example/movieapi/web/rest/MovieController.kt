package com.example.movieapi.web.rest

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MovieController (
    private val movieService: MovieService
) {

    @PostMapping("/test")
    fun createMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO> {
        return ResponseEntity(movieService.createMovie(movieDTO), HttpStatus.OK)
    }

    @GetMapping("/list")
    fun getMovies(): ResponseEntity<List<MovieDTO>> {
        return ResponseEntity(movieService.getMovies(), HttpStatus.OK)
    }

    @GetMapping("/list/{id}")
    fun getMovie(@PathVariable id: Int)  {
        ResponseEntity.ok(movieService.getMovie(id))
    }
}