package com.alexzh.popularmovies.di

import com.alexzh.popularmovies.R
import com.alexzh.popularmovies.data.repository.MovieDbPopularMoviesRepository
import com.alexzh.popularmovies.data.repository.PopularMoviesRepository
import com.alexzh.popularmovies.data.service.PopularMoviesServiceFactory
import com.alexzh.popularmovies.movies.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel(override = true) { MoviesViewModel(repository = get()) }
}

val dataModule = module {
    factory(override = true) { PopularMoviesServiceFactory().createPopularMoviesService(true, androidContext().getString(R.string.the_movie_db_api_key)) }
    factory(override = true) { MovieDbPopularMoviesRepository(service = get()) as PopularMoviesRepository }
}