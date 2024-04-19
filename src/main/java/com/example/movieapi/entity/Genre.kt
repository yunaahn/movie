package com.example.movieapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull


@Entity
data class Genre(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id:Long? = null,


  var name:String = ""
)
