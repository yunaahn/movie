package com.example.movieapi.web.rest

import com.example.movieapi.dto.RatingDTO
import com.example.movieapi.service.RatingService
import com.example.movieapi.service.RedisRatingService
import com.example.movieapi.utils.mapper.RatingMapper
import lombok.extern.log4j.Log4j2
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Log4j2
@RestController
@RequestMapping("/rating")
class RatingController (
    private val ratingRedisService: RedisRatingService,
    private val ratingService: RatingService,
    private val ratingMapper: RatingMapper
) {

    val logger = LogManager.getLogger(RatingController::class.java)!!

//    @PostMapping("/add")
//    fun addRating(@RequestBody ratingDTO: RatingDTO) : ResponseEntity<RatingDTO> {
//        return ResponseEntity(ratingService.createRating(ratingDTO), HttpStatus.OK)
//    }
//
//    @PostMapping("/save")
//    fun saveRating(@RequestBody ratingDTO: RatingDTO): ResponseEntity<Any> {
//        val rating = ratingMapper.toEntity(ratingDTO)
//        logger.info(rating.toString())
//        ratingRedisService.saveRating(rating)
//        return ResponseEntity.ok().build()
//    }
//
//    @GetMapping("/{id}")
//    fun findRatingById(@PathVariable id: Long): ResponseEntity<Any> {
//        val rating = ratingRedisService.findRatingById(id)
//        return if (rating != null) {
//            ResponseEntity.ok(rating)
//        } else {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating not found")
//        }
//    }
}