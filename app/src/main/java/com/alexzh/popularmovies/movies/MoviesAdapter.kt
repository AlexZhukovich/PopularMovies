package com.alexzh.popularmovies.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexzh.popularmovies.R
import com.alexzh.popularmovies.data.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun addMovies(movies: List<Movie>) {
        this.movies += movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int  = movies.size

    class MovieViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w185${movie.posterPath}")
                .into(itemView.movieLogo)
            itemView.movieTitle.text = movie.title
        }
    }
}