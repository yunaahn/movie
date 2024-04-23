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
    var genre_id: Long? = null // genre_id를 직접 저장하는 필드

) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    var genre: Genre? = null
}
