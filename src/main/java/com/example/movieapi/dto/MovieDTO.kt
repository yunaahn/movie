package com.example.movieapi.dto

import org.jetbrains.annotations.NotNull

data class MovieDTO(


    val id:Long,


    var name: String,


    var rating:Int,

    val genre_id: Long?
)
