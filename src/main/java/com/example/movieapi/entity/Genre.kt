package com.example.movieapi.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull


@Entity
data class Genre(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "genre_id")
  var id:Long,


  var name:String = ""
)
