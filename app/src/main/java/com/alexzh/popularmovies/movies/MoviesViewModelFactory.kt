package com.alexzh.popularmovies.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexzh.popularmovies.data.repository.PopularMoviesRepository

class MoviesViewModelFactory(
    private val repository: PopularMoviesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(repository) as T
    }
}