package com.alexzh.popularmovies.di

import com.alexzh.popularmovies.data.repository.PopularMoviesRepository
import com.alexzh.popularmovies.movies.MoviesViewModel
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testAppModule = module {
    viewModel(override = true) { MoviesViewModel(repository = get()) }
}

val testDataModule = module {
    single(override = true) { mockk<PopularMoviesRepository>() }
}