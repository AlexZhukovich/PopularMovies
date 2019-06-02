package com.alexzh.popularmovies.data.repository

import com.alexzh.popularmovies.data.model.PopularMoviesInfo
import io.reactivex.Observable

interface PopularMoviesRepository {

    fun fetchMovies(apiKey: String,
                    language: String,
                    page: Int) : Observable<PopularMoviesInfo>
}