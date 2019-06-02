package com.alexzh.popularmovies.data.repository

import com.alexzh.popularmovies.data.model.PopularMoviesInfo
import com.alexzh.popularmovies.data.service.PopularMoviesService
import io.reactivex.Observable

class MovieDbPopularMoviesRepository(private val service: PopularMoviesService) : PopularMoviesRepository {

    override fun fetchMovies(apiKey: String, language: String, page: Int): Observable<PopularMoviesInfo> {
        return service.fetchPopularMovies(apiKey, language, page)
    }
}