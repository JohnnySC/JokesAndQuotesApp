package com.github.johnnysc.jokeapp

/**
 * @author Asatryan on 17.06.2021
 */
//class BaseViewModelTest {
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun test_get_joke_from_cloud_success(): Unit = runBlocking {
////        val model = TestJokeRepository()
////        val communication = TestCommunication()
////        val viewModel = BaseViewModel(model, communication, TestCoroutineDispatcher())
////
////        model.success = true
////        viewModel.chooseFavorites(false)
////        viewModel.getItem()
////
////        val actualText = communication.text
////        val actualId = communication.id
////        val expectedText = "cloudJokeText\ncloudJokePunchline"
////        assertEquals(expectedText, actualText)
////        assertNotEquals(0, actualId)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun test_get_joke_from_cloud_fail(): Unit = runBlocking {
////        val model = TestJokeRepository()
////        val communication = TestCommunication()
////        val viewModel = BaseViewModel(model, communication, TestCoroutineDispatcher())
////
////        model.success = false
////        viewModel.chooseFavorites(false)
////        viewModel.getJoke()
////
////        val actualText = communication.text
////        val actualId = communication.id
////        val expectedText = "no connection"
////        val expectedId = 0
////        assertEquals(expectedText, actualText)
////        assertEquals(expectedId, actualId)
//    }
//
//
//    private inner class TestJokeRepository : JokeRepository {
//
//        private val cacheJokeUiModel = BaseCommonUiModel("cachedJokeText", "cachedJokePunchline")
//        private val cacheJokeFailure = FailedCommonUiModel("cacheFailed")
//        private val cloudJokeUiModel = BaseCommonUiModel("cloudJokeText", "cloudJokePunchline")
//        private val cloudJokeFailure = FailedCommonUiModel("no connection")
//        var success: Boolean = false
//        private var getFromCache = false
//        private var cachedJoke: CommonUiModel? = null
//
//        override suspend fun getJoke(): CommonUiModel {
//            return if (success) {
//                if (getFromCache) {
//                    cacheJokeUiModel.also {
//                        cachedJoke = it
//                    }
//                } else {
//                    cloudJokeUiModel.also {
//                        cachedJoke = it
//                    }
//                }
//            } else {
//                cachedJoke = null
//                if (getFromCache) {
//                    cacheJokeFailure
//                } else {
//                    cloudJokeFailure
//                }
//            }
//        }
//
//        override suspend fun changeJokeStatus(): CommonUiModel? {
//            return null
////            return when (cachedJoke) {
////                is BaseJokeUiModel -> {
////                    FavoriteJokeUiModel(cachedJoke!!.getData().first, "")
////                }
////                is FavoriteJokeUiModel -> {
////                    BaseJokeUiModel(cachedJoke!!.getData().first, "")
////                }
////                else -> null
////            }
//        }
//
//        override fun chooseDataSource(cached: Boolean) {
//            getFromCache = cached
//        }
//    }
//
//    private inner class TestCommunication : Communication {
//        var text = ""
//        var id = -1
//        var observedCount = 0
//        override fun showData(data: Pair<String, Int>) {
//            text = data.first
//            id = data.second
//        }
//
//        override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
//            observedCount++
//        }
//    }
//}