package com.alexzh.popularmovies.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PopularMoviesServiceFactory {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    fun createPopularMoviesService(isDebug: Boolean, apiKey: String): PopularMoviesService {
        val okHttpClient = createOkHttpClient(createLoggingInterceptor(isDebug), apiKey)
        return createPopularMoviesService(okHttpClient)
    }

    private fun createPopularMoviesService(okHttpClient: OkHttpClient): PopularMoviesService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PopularMoviesService::class.java)
    }

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                                   apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(createApiInterceptor(apiKey))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun createApiInterceptor(apiKey: String) : Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val requestBuilder = originalRequest.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}