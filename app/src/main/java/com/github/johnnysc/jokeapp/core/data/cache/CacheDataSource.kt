package com.github.johnnysc.jokeapp.core.data.cache

import com.github.johnnysc.jokeapp.core.data.DataFetcher
import com.github.johnnysc.jokeapp.core.data.ChangeStatus
import com.github.johnnysc.jokeapp.data.CommonDataModel

/**
 * @author Asatryan on 15.06.2021
 **/
interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {
    suspend fun getDataList(): List<CommonDataModel<E>>
    suspend fun remove(id: E)
}