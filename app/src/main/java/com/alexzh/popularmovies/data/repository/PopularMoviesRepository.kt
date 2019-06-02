package com.alexzh.popularmovies.data.repository

import com.alexzh.popularmovies.data.model.PopularMoviesInfo
import io.reactivex.Single

interface PopularMoviesRepository {

    fun fetchMovies(language: String,
                    page: Int) : Single<PopularMoviesInfo>
}