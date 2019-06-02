package com.alexzh.popularmovies.movies

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.alexzh.popularmovies.R
import com.alexzh.popularmovies.data.model.Movie
import com.alexzh.popularmovies.data.model.PopularMoviesInfo
import com.alexzh.popularmovies.data.repository.PopularMoviesRepository
import com.alexzh.popularmovies.di.testAppModule
import com.alexzh.popularmovies.di.testDataModule
import com.alexzh.popularmovies.matchers.RecyclerViewMatchers
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class MoviesActivityTest : KoinTest {

    private val repository: PopularMoviesRepository by inject()

    @Rule @JvmField
    val activityRule = ActivityTestRule<MoviesActivity>(MoviesActivity::class.java, true, false)

    @Before
    fun setUp() {
        loadKoinModules(testDataModule, testAppModule)
    }

    @Test
    fun shouldDisplayOnlyOneMovie() {
        val popularMoviesInfo: PopularMoviesInfo = mockk()
        val movie = mockk<Movie>()
        every { movie.title } returns "Test item"
        every { movie.posterPath } returns ""
        every { popularMoviesInfo.movies } returns listOf(movie)

        every { repository.fetchMovies(eq("en-US"), eq(1)) } returns Single.just(popularMoviesInfo)
        activityRule.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(RecyclerViewMatchers.withItemCount(1)))
    }

    @Test
    fun shouldDisplayMovieWithTestItemTitle() {
        val popularMoviesInfo: PopularMoviesInfo = mockk()
        val movie = mockk<Movie>()
        every { movie.title } returns "Test item"
        every { movie.posterPath } returns ""
        every { popularMoviesInfo.movies } returns listOf(movie)

        every { repository.fetchMovies(eq("en-US"), eq(1)) } returns Single.just(popularMoviesInfo)
        activityRule.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(RecyclerViewMatchers.withItemText("Test item")))
    }

}