package com.example.movieapi.web.rest

import com.example.movieapi.dto.GenreDTO
import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.service.GenreService
import com.example.movieapi.service.GenreServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(("/genre"))
class GenreController(private val genreService: GenreService) {

    @PostMapping("/add")
    fun createGenre(@RequestBody genreDTO: GenreDTO) : ResponseEntity<GenreDTO> {
        return ResponseEntity(genreService.createGenre(genreDTO), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{genreId}")
    fun deleteGenre(@PathVariable genreId: Long) {
        genreService.deleteGenre(genreId)
    }
}