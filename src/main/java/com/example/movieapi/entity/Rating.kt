package com.example.movieapi.entity

import jakarta.persistence.*
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable


@Entity
data class Rating(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "user_id")
    var userId: Long?,

    @Column(name = "movie_id")
    var movieId: Long,

    var rating:Long


)