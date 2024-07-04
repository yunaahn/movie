package com.example.movieapi.dto

import com.example.movieapi.entity.UploadFile
import org.jetbrains.annotations.NotNull

data class MovieDTO(


    val id: Long = 0,
    var name: String = "",
    var rating: Int = 0,
    val genreId: Long? = null,
    var attachFile: UploadFile = UploadFile("", "")


    )
