package com.alexzh.popularmovies.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("video") val isVideoAvailable: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("title") val title: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("adult") val isAdultAvailable: Boolean,
    @SerializedName("release_date") val releaseDate: String
)