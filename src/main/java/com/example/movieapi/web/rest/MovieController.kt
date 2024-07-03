package com.example.movieapi.web.rest

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.entity.Movie
import com.example.movieapi.entity.MovieWithRating
import com.example.movieapi.repository.RatingRepository
import com.example.movieapi.service.MovieService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.log4j.Log4j2
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Log4j2
@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = arrayOf("http://localhost:5175"))
class MovieController (
    private val movieService: MovieService,
    private val ratingRepository: RatingRepository
) {

    @Value("\${file.dir}")
    private lateinit var fileDir: String


    private final val logger = LoggerFactory.getLogger(javaClass)
    // 영화 추가
    @PostMapping("/add")
    fun createMovie(
        @RequestParam("file") file: MultipartFile,
        @RequestBody movieDTO: String,
        request: HttpServletRequest
    ): ResponseEntity<MovieDTO> {
        // movieDTO를 직접 파싱
        val objectMapper = ObjectMapper()
        val movieDTOObj = objectMapper.readValue(movieDTO, MovieDTO::class.java)

        logger.debug("genre_id = {}", movieDTOObj.genre_id)
        logger.debug("name = {}", movieDTOObj.name)

        logger.info("request = {}", request)
        logger.info("file = {}", file)

        if (!file.isEmpty) {
            val fullPath = fileDir + file.originalFilename
            logger.info("fullPath = {}", fullPath)
            file.transferTo(File(fullPath))
            movieDTOObj.attachFileName = file.originalFilename // 파일명 저장
        }

        val savedMovie = movieService.createMovie(movieDTOObj)
        return ResponseEntity.ok(savedMovie)
    }

//    @PostMapping("/upload")
//    fun saveFile(
//        @RequestParam("itemName") itemName: String,
//        @RequestParam("file") file: MultipartFile,
//        request: HttpServletRequest
//    ): String {
//        logger.info("request = {}", request)
//        logger.info("fileName = {}", itemName)
//        logger.info("file = {}", file)
//
//        if (!file.isEmpty) {
//            val fullPath = fileDir + file.originalFilename
//            logger.info("fullPath = {}", fullPath)
//            file.transferTo(File(fullPath))
//        }
//
//        return "redirect:/"
//    }




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