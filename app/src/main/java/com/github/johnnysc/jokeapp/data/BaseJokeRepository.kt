package com.github.johnnysc.jokeapp.data

import com.github.johnnysc.jokeapp.data.cache.CacheDataSource
import com.github.johnnysc.jokeapp.data.cache.CachedJoke
import com.github.johnnysc.jokeapp.data.net.CloudDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
) : JokeRepository {
    private var currentDataSource: JokeDataFetcher = cloudDataSource
    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }
    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO) {
        try {
            val joke = currentDataSource.getJoke()
            cachedJoke.saveJoke(joke)
            return@withContext joke
        } catch (e: Exception) {
            cachedJoke.clear()
            throw e
        }
    }
    override suspend fun changeJokeStatus(): JokeDataModel = cachedJoke.change(cacheDataSource)
}