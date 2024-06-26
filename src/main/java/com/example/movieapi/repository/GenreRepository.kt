package com.example.movieapi.repository

import com.example.movieapi.entity.Genre
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : CrudRepository<Genre, Long> {}