package com.example.movieapi.dto

import com.example.movieapi.entity.UploadFile
import org.jetbrains.annotations.NotNull

data class MovieDTO(


    val id:Long,

    var name: String,

    val genre_id: Long?,

    var attachFileName: String?


    )
