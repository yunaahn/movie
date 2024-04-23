package com.example.movieapi.repository

import com.example.movieapi.entity.Genre
import org.springframework.data.repository.CrudRepository

interface GenreRepository : CrudRepository<Genre, Long> {}