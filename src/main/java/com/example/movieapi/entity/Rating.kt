package com.example.movieapi.entity

import jakarta.persistence.*
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable


@Entity
data class Rating(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    @Column(name = "user_id")
    var user_id: Long,

    @Column(name = "movie_id")
    var movie_id: Long,

    var rating:Int


)