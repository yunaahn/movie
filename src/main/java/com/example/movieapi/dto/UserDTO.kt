package com.example.movieapi.dto

import jakarta.validation.constraints.NotNull

data class UserDTO(

    var id:Long,


    var name:String = "",

    var password:String = ""

)
