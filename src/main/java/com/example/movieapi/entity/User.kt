package com.example.movieapi.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long ,


    var name:String = "",

    var password:String = ""
)
