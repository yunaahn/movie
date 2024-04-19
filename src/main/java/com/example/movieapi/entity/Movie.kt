package com.example.movieapi.entity

import jakarta.persistence.*


@Entity
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ,

    var name: String,

    var rating:Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "genre_id")
    var genre: Genre? = null


)
