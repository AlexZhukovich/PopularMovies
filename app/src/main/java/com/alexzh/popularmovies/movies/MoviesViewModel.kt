package com.alexzh.popularmovies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexzh.popularmovies.data.model.Movie
import com.alexzh.popularmovies.data.repository.PopularMoviesRepository
import com.alexzh.popularmovies.state.Resource
import com.alexzh.popularmovies.state.ResourceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel(private val repository: PopularMoviesRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    private var movies: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return movies
    }

    fun fetchMovies() {
        movies.postValue(Resource(ResourceState.LOADING, movies.value?.data, null))
        val language = "en-US"
        val page = 1

        disposable.add(
            repository.fetchMovies(language, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { movies.postValue(Resource(ResourceState.SUCCESS, it.movies, null)) },
                    {
                        movies.postValue(Resource(ResourceState.ERROR, null, it.cause))
                        it.printStackTrace()
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}