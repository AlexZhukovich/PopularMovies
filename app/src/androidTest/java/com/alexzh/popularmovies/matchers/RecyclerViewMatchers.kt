package com.alexzh.popularmovies.matchers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import com.alexzh.popularmovies.R
import com.alexzh.popularmovies.movies.MoviesAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object RecyclerViewMatchers {
    fun withItemCount(count: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                val recyclerView = view as RecyclerView
                val adapter = recyclerView.adapter
                return adapter?.itemCount == count
            }

            override fun describeTo(description: Description) {
                description.appendText("RecyclerView should have $count items")
            }
        }
    }

    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    fun withItemText(text: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                val recyclerView = view as RecyclerView
                val adapter = recyclerView.adapter
                val itemCount = adapter?.itemCount!!
                for (pos in 0..itemCount) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(pos) as MoviesAdapter.MovieViewHolder
                    val noteTextView = viewHolder.itemView.findViewById<TextView>(R.id.movieTitle)
                    if (noteTextView.text.toString() == text) {
                        return true
                    }
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("RecyclerView should have item with text: $text")
            }
        }
    }
}