package com.example.movieapi.repository

import com.example.movieapi.entity.Rating
import com.example.movieapi.web.rest.RatingController
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RatingRedisRepository (
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    val logger = LogManager.getLogger(RatingController::class.java)!!

    fun saveRating(rating: Rating) {
        val ratingJson = objectMapper.writeValueAsString(rating)
        logger.info(ratingJson.toString())
        redisTemplate.opsForValue().set("rating:${rating.id}", ratingJson)
    }

    fun findRatingById(id: Long): Rating? {
        val ratingJson = redisTemplate.opsForValue().get("rating:$id")
        return if (ratingJson != null) {
            objectMapper.readValue(ratingJson, Rating::class.java)
        } else {
            null
        }
    }

    // 다른 엔티티에 대한 저장 및 조회 메서드도 추가 가능
}