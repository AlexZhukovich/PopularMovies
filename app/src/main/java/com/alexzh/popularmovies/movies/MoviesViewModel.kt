package com.alexzh.popularmovies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexzh.popularmovies.data.model.Movie

class MoviesViewModel : ViewModel() {

    private var movies: LiveData<List<Movie>> = MutableLiveData()

    private fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    private fun fetchMovies() {

    }

}