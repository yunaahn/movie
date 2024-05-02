package com.example.movieapi.dto

import com.example.movieapi.persistance.AllOpen
import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@AllOpen
@JsonIgnoreProperties(ignoreUnknown=true)
class MovieRequest (

    val id:Long,

    var name: String,

    var rating:Long,

    val genre_id: Long?
)