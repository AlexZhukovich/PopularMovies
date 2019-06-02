package com.alexzh.popularmovies.data.repository

import com.alexzh.popularmovies.data.model.PopularMoviesInfo
import com.alexzh.popularmovies.data.service.PopularMoviesService
import io.reactivex.Single

class MovieDbPopularMoviesRepository(private val service: PopularMoviesService) : PopularMoviesRepository {

    override fun fetchMovies(language: String, page: Int): Single<PopularMoviesInfo> {
        return service.fetchPopularMovies(language, page)
    }
}