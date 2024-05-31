package com.example.movieapi.dto

import com.example.movieapi.entity.UploadFile
import org.jetbrains.annotations.NotNull

data class MovieDTO(


    val id:Long,

    var name: String,

    var rating:Int,

    val genre_id: Long?,

    var attachFile: UploadFile = UploadFile("", "")


    )
