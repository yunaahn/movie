package com.example.movieapi.web.rest

import com.example.movieapi.dto.GenreDTO
import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.service.GenreService
import com.example.movieapi.service.GenreServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(("/genre"))
@Tag(name = "genre", description = "genre CRUD API")
class GenreController(private val genreService: GenreService) {

    @PostMapping("/add")
    @Operation(summary = "projectId로 보드 조회", description = "projectId로 보드를 조회한다")
    @Parameter(name = "genreDTO", description = "프로젝트 ID", example = "216")
    fun createGenre(@RequestBody genreDTO: GenreDTO) : ResponseEntity<GenreDTO> {
        return ResponseEntity(genreService.createGenre(genreDTO), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{genreId}")
    fun deleteGenre(@PathVariable genreId: Long) {
        genreService.deleteGenre(genreId)
    }
}