package com.example.movieapi.entity

import jakarta.persistence.*


@Entity
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    var name: String,

    var rating: Int,

    @Column(name = "genre_id") // genre_id 컬럼과 매핑
    var genreId: Long? = null, // genre_id를 직접 저장하는 필드

    var attachFileName: String?

)