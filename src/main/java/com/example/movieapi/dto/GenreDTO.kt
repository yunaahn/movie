package com.example.movieapi.dto

import jakarta.validation.constraints.NotNull

data class GenreDTO(


    var id:Long ,


    var name:String = ""
)
