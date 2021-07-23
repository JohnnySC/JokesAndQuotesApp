package com.github.johnnysc.jokeapp.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Asatryan on 23.07.2021
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class RandomJokesTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test() {
        onView(withText("GET JOKE")).perform(click())
        onView(withText("mock text 0\nmock punchline 0")).check(matches(isDisplayed()))
        onView(withText("GET JOKE")).perform(click())
        onView(withText("mock text 1\nmock punchline 1")).check(matches(isDisplayed()))
    }
}