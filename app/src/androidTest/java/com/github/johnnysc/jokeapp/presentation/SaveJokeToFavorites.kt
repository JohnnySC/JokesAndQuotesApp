package com.github.johnnysc.jokeapp.presentation

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.core.lazyActivityScenarioRule
import com.github.johnnysc.jokeapp.data.cache.BaseRealmProvider
import org.junit.*
import org.junit.runner.RunWith

/**
 * @author Asatryan on 23.07.2021
 **/
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class SaveJokeToFavorites {

    @get:Rule
    val activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    @Before
    fun before() {
        val realmProvider = BaseRealmProvider(ApplicationProvider.getApplicationContext(), true)

        realmProvider.provide().use {
            it.executeTransaction {
                it.deleteAll()
            }
        }
        activityTestRule.launch(
            Intent(
                ApplicationProvider.getApplicationContext(),
                MainActivity::class.java
            )
        )
    }

    @Test
    fun test() {
        onView(withText("No favorites! Add one by heart icon")).check(matches(isDisplayed()))
        onView(withText("GET JOKE")).perform(click())
        onView(withText("mock text 0\nmock punchline 0")).check(matches(isDisplayed()))

        onView(withId(R.id.changeButton)).perform(click())
        onView(withId(R.id.commonDataTextView)).check(matches(withText("mock text 0\nmock punchline 0")))
    }
}
