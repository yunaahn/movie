package com.example.movieapi.service

import com.example.movieapi.entity.Rating
import com.example.movieapi.repository.RatingRedisRepository
import com.example.movieapi.repository.RatingRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class RedisRatingService(
//    private val ratingRepository: RatingRepository,
//    private val ratingRedisRepository: RatingRedisRepository,
//    private val redisTemplate: RedisTemplate<String, JvmType.Object>,
//    private val redisMessageListenerContainer: RedisMessageListenerContainer
) {
//    fun saveRating(rating: Rating) {
//
//        val logger = LogManager.getLogger(RatingController::class.java)!!
//        logger.info(rating)
//
//        ratingRedisRepository.saveRating(rating)
//    }
//
//    fun findRatingById(id: Long): Rating? {
//        // Redis에서 조회
//        var rating = ratingRedisRepository.findRatingById(id)
//
//        // Redis에 없는 경우 데이터베이스에서 조회
//        if (rating == null) {
//            rating = ratingRepository.findById(id).orElse(null)
//            // 데이터베이스에서 조회한 결과가 있다면 Redis에 저장
//            if (rating != null) {
//                ratingRedisRepository.saveRating(rating)
//            }
//        }
//        return rating
//    }
}
