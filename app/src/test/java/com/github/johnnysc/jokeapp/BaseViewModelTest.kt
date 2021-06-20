package com.github.johnnysc.jokeapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.jokeapp.data.JokeRepository
import com.github.johnnysc.jokeapp.presentation.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Asatryan on 17.06.2021
 */
class BaseViewModelTest {

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_joke_from_cloud_success(): Unit = runBlocking {
        val model = TestJokeRepository()
        val communication = TestCommunication()
        val viewModel = BaseViewModel(model, communication, TestCoroutineDispatcher())

        model.success = true
        viewModel.chooseFavorites(false)
        viewModel.getJoke()

        val actualText = communication.text
        val actualId = communication.id
        val expectedText = "cloudJokeText\ncloudJokePunchline"
        assertEquals(expectedText, actualText)
        assertNotEquals(0, actualId)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_joke_from_cloud_fail(): Unit = runBlocking {
        val model = TestJokeRepository()
        val communication = TestCommunication()
        val viewModel = BaseViewModel(model, communication, TestCoroutineDispatcher())

        model.success = false
        viewModel.chooseFavorites(false)
        viewModel.getJoke()

        val actualText = communication.text
        val actualId = communication.id
        val expectedText = "no connection"
        val expectedId = 0
        assertEquals(expectedText, actualText)
        assertEquals(expectedId, actualId)
    }


    private inner class TestJokeRepository : JokeRepository {

        private val cacheJokeUiModel = BaseJokeUiModel("cachedJokeText", "cachedJokePunchline")
        private val cacheJokeFailure = FailedJokeUiModel("cacheFailed")
        private val cloudJokeUiModel = BaseJokeUiModel("cloudJokeText", "cloudJokePunchline")
        private val cloudJokeFailure = FailedJokeUiModel("no connection")
        var success: Boolean = false
        private var getFromCache = false
        private var cachedJoke: JokeUiModel? = null

        override suspend fun getJoke(): JokeUiModel {
            return if (success) {
                if (getFromCache) {
                    cacheJokeUiModel.also {
                        cachedJoke = it
                    }
                } else {
                    cloudJokeUiModel.also {
                        cachedJoke = it
                    }
                }
            } else {
                cachedJoke = null
                if (getFromCache) {
                    cacheJokeFailure
                } else {
                    cloudJokeFailure
                }
            }
        }

        override suspend fun changeJokeStatus(): JokeUiModel? {
            return null
//            return when (cachedJoke) {
//                is BaseJokeUiModel -> {
//                    FavoriteJokeUiModel(cachedJoke!!.getData().first, "")
//                }
//                is FavoriteJokeUiModel -> {
//                    BaseJokeUiModel(cachedJoke!!.getData().first, "")
//                }
//                else -> null
//            }
        }

        override fun chooseDataSource(cached: Boolean) {
            getFromCache = cached
        }
    }

    private inner class TestCommunication : Communication {
        var text = ""
        var id = -1
        var observedCount = 0
        override fun showData(data: Pair<String, Int>) {
            text = data.first
            id = data.second
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
            observedCount++
        }
    }
}