package com.alexzh.popularmovies

import android.app.Application
import com.alexzh.popularmovies.di.appModule
import com.alexzh.popularmovies.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PopularMoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PopularMoviesApp)
            modules(listOf(
                dataModule,
                appModule
            ))
        }
    }
}