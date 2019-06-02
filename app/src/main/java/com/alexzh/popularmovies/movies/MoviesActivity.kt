package com.alexzh.popularmovies.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexzh.popularmovies.R
import com.alexzh.popularmovies.data.model.Movie
import com.alexzh.popularmovies.state.Resource
import com.alexzh.popularmovies.state.ResourceState
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity() {

    private val adapter: MoviesAdapter by lazy { MoviesAdapter() }
    private val viewModel: MoviesViewModel by inject()

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