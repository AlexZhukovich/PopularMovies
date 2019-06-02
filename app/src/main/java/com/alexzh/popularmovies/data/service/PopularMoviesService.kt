package com.alexzh.popularmovies.data.service

import com.alexzh.popularmovies.data.model.PopularMoviesInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesService {

    @GET("movie/popular")
    fun fetchPopularMovies(@Query("api_key") apiKey: String,
                           @Query("language") language: String,
                           @Query("page") page: Int) : Observable<PopularMoviesInfo>
}