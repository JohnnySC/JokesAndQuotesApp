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
class BaseRepository<E>(
    private val cacheDataSource: CacheDataSource<E>,
    private val cloudDataSource: CloudDataSource<E>,
    private val cached: CachedData<E>
) : CommonRepository<E> {
    private var currentDataSource: DataFetcher<E> = cloudDataSource
    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }

    override suspend fun getCommonItem(): CommonDataModel<E> = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cached.save(data)
            return@withContext data
        } catch (e: Exception) {
            cached.clear()
            throw e
        }
    }

    override suspend fun changeStatus(): CommonDataModel<E> = cached.change(cacheDataSource)
}