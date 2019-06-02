package com.alexzh.popularmovies.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexzh.popularmovies.R
import com.alexzh.popularmovies.data.model.Movie
import com.alexzh.popularmovies.data.repository.MovieDbPopularMoviesRepository
import com.alexzh.popularmovies.data.repository.PopularMoviesRepository
import com.alexzh.popularmovies.data.service.PopularMoviesServiceFactory
import com.alexzh.popularmovies.state.Resource
import com.alexzh.popularmovies.state.ResourceState
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    private val adapter: MoviesAdapter by lazy { MoviesAdapter() }
    private val repository: PopularMoviesRepository by lazy {
        val service = PopularMoviesServiceFactory().createPopularMoviesService(true, getString(R.string.the_movie_db_api_key))
        MovieDbPopularMoviesRepository(service)
    }
    private val viewModel: MoviesViewModel by lazy { ViewModelProviders.of(this, MoviesViewModelFactory(repository)).get(MoviesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMovies().observe(this, Observer<Resource<List<Movie>>>{
            when(it.status) {
                ResourceState.LOADING -> {
                    // TODO: implement it
                }
                ResourceState.SUCCESS -> {
                    if (it.data != null) {
                        adapter.addMovies(it.data)
                    }
                }
                ResourceState.ERROR -> {
                    // TODO: implement it
                }
            }
        })
        viewModel.fetchMovies()
    }
}