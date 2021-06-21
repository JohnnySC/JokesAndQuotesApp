package com.github.johnnysc.jokeapp.data

import com.github.johnnysc.jokeapp.core.data.DataFetcher
import com.github.johnnysc.jokeapp.core.data.CommonRepository
import com.github.johnnysc.jokeapp.core.data.cache.CacheDataSource
import com.github.johnnysc.jokeapp.core.data.cache.CachedData
import com.github.johnnysc.jokeapp.core.data.net.CloudDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cached: CachedData
) : CommonRepository {
    private var currentDataSource: DataFetcher = cloudDataSource
    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }

    override suspend fun getCommonItem(): CommonDataModel = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cached.save(data)
            return@withContext data
        } catch (e: Exception) {
            cached.clear()
            throw e
        }
    }

    override suspend fun changeStatus(): CommonDataModel = cached.change(cacheDataSource)
}