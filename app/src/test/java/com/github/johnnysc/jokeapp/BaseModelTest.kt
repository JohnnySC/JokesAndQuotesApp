package com.github.johnnysc.jokeapp

import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * @author Asatryan on 16.06.2021
 */
class BaseModelTest {

    @Test
    fun test_change_data_source(): Unit = runBlocking {
//        val cacheDataSource = TestCacheDataSource()
//        val cloudDataSource = TestCloudDataSource()
//        val model = BaseModel(cacheDataSource, cloudDataSource, TestResourceManager())
//        model.chooseDataSource(false)
//        cloudDataSource.getJokeWithResult(true)
//        val joke = model.getJoke()
//        assertEquals(joke is BaseJokeUiModel, true)
//        model.changeJokeStatus()
//        assertEquals(cacheDataSource.checkContainsId(0), true)
    }

//    private inner class TestCacheDataSource : CacheDataSource {
//
//        private val map = HashMap<Int, Joke>()
//        private var success: Boolean = true
//        private var nextJokeIdToGet = -1
//
//        fun getNextJokeWithResult(success: Boolean, id: Int) {
//            this.success = success
//            nextJokeIdToGet = id
//        }
//
//        override suspend fun getJoke(): Result<Joke, Unit> {
//            return if (success) {
//                Result.Success(map[nextJokeIdToGet]!!)
//            } else {
//                Result.Error(Unit)
//            }
//        }
//
//        override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
//            return if (map.containsKey(id)) {
//                val result = map[id]!!.toBaseJoke()
//                map.remove(id)
//                result
//            } else {
//                map[id] = joke
//                joke.toFavoriteJoke()
//            }
//        }
//
//        fun checkContainsId(id: Int) = map.containsKey(id)
//    }
//
//    private inner class TestCloudDataSource : CloudDataSource {
//
//        private var success = true
//        private var count = 0
//
//        fun getJokeWithResult(success: Boolean) {
//            this.success = success
//        }
//
//        override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
//            return if (success) {
//                Result.Success(JokeServerModel(count++, "type", "text$count", "punchline$count"))
//            } else {
//                Result.Error(ErrorType.NO_CONNECTION)
//            }
//        }
//    }
//
//    private inner class TestResourceManager : ResourceManager {
//        val message: String = ""
//        override fun getString(stringResId: Int) = message
//    }
}