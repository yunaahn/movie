package com.example.movieapi.repository

import com.example.movieapi.entity.Movie
import com.example.movieapi.entity.QMovie
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository


@Repository
class MovieRepositoryImpl (
    private val entityManager: EntityManager,
    private val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Movie::class.java), CustomMovieRepository {

    private val movie: QMovie = QMovie.movie

    override fun findAllMovies(): List<Movie> {
        return queryFactory.selectFrom(movie)
            .fetch()
    }

    override fun findAllMoviesByName(keyword: String): List<Movie> {
        return queryFactory.selectFrom(movie)
            .where(nameContains(keyword))
            .orderBy(movie.name.asc())
            .fetch()
    }

    override fun findAllMoviesByRating(keyword: String): List<Movie> {
        return queryFactory.selectFrom(movie)
            .where(nameContains(keyword))
            .orderBy(movie.rating.desc())
            .fetch()
    }

    override fun searchMoviesByNameContaining(keyword: String): List<Movie> {
        return queryFactory.selectFrom(movie)
            .where(nameContains(keyword))
            .fetch()
    }

    private fun nameContains(keyword: String): BooleanExpression {
        return movie.name.containsIgnoreCase(keyword)
    }
}